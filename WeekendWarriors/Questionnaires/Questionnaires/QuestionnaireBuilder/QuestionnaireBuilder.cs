using Questionnaires.AST.Visitor;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.AST;
using Questionnaires.AST.Operators;
using Questionnaires.Renderer;
using Questionnaires.VariableStore;
using Questionnaires.Value;
using System.ComponentModel;

namespace Questionnaires.QuestionaireBuilder
{
    class QuestionnaireBuilder 
    {        
        private VariableStore.VariableStore VariableStore;
        private Renderer.Renderer Renderer;
        private RuleContainer.RuleContainer RuleContainer;
        private ExpressionEvaluator.Evaluator ExpressionEvaluator;
        public QuestionnaireBuilder(VariableStore.VariableStore variableStore, Renderer.Renderer renderer, RuleContainer.RuleContainer ruleContainer)
        {
            VariableStore = variableStore;
            Renderer = renderer;
            RuleContainer = ruleContainer;
            ExpressionEvaluator = new Questionnaires.ExpressionEvaluator.Evaluator(VariableStore);

            // Connect runtime components
            VariableStore.VariableChanged += VariableStore_VariableChanged;
        }

        public void Build(Form node)
        {
            foreach (var statement in node.Statements)
            {
                // Visit all of the child nodes. Pass a dummy function that always returns true as the visibility function
                Visit((dynamic)statement, new Func<bool>(() => { return true; }));
            }

            // For a form all we want to do is just change the window title
            // you cannot change this later through user input
            Renderer.SetWindowTitle(node.Identifier);
        }

        
        public void Visit(ComputedQuestion node, Func<bool> visibilityCondition)
        {
            // Make sure to visit the question child node to add it to the renderer
            Visit(node.Question, visibilityCondition);

            // Add the question to the variable store. 
            // Todo: We are using the expression validator to get the type of the expression here, but since we are not in runtime yet. That makes no sense
            // it would make more sense to pass QLContext into this object and get the type from there. I do feel that we need to have an addValue function 
            // to make a distinction between adding a variable (initialization time, no real value is know, just the type) and setting a value (run time, actual value is known)
            // Second thing: If we add these things to the store at runtime (see rule below), do we really need to add it here as well?
            VariableStore.SetValue(node.Question.Identifier, ExpressionEvaluator.Evaluate(node.Expression));            

            RuleContainer.AddRule(
                new Action<IVariableStore, Renderer.Renderer>((variableStore, renderer) =>
                {
                    if (visibilityCondition())
                    {
                        variableStore.SetValue(node.Question.Identifier, ExpressionEvaluator.Evaluate(node.Expression));
                    }
                    else
                    {
                        // TODO: we are not quite ready for this since we don't handle values that are not present in the variableStore
                        //variableStore.RemoveValue(node.Question.Identifier);
                    }
                }));
        }
        
        public void Visit(AST.Question node, Func<bool> visibilityCondition)
        {
            // Build a question object from the information in the AST node
            var question = new Question.Question(node.Identifier, node.Body, node.Type);
            // Add the question to the renderer
            Renderer.AddQuestion(question);
            // And the variable store
            VariableStore.SetValue(question.Name, question.Value);

            if(visibilityCondition != null)
            {
                // Add a rule to the rule container that sets the visibility for this question
                RuleContainer.AddRule(
                    new Action<IVariableStore, Renderer.Renderer>((variableStore, renderer) =>
                    {
                        if (visibilityCondition())
                        {
                            renderer.SetVisibility(question.Name, true);
                        }
                        else
                        {
                            renderer.SetVisibility(question.Name, false);
                        }
                    })
                );
            }
        }     

        public void Visit(Conditional node, Func<bool> visibilityCondition)
        {
            /* The conditional node. This is where we need to do some real work. We need to make function objects
             * That evaluate the condition and based on the outcome set the visibility of questions */

            Func<bool> conditionFunctionThen = new Func<bool>(() => 
            {
                return visibilityCondition() && (ExpressionEvaluator.Evaluate(node.Condition) as BoolValue).GetValue();
            }
            );

            Func<bool> conditionFunctionElse = new Func<bool>(() =>
            {
                return visibilityCondition() && !(ExpressionEvaluator.Evaluate(node.Condition) as BoolValue).GetValue();
            }
            );

            foreach (var thenStatement in node.ThenStatements)
            {
                Visit((dynamic)thenStatement, conditionFunctionThen);               
            }

            foreach (var elseStatement in node.ElseStatements)
            {
                Visit((dynamic)elseStatement, conditionFunctionElse);
            }            
        }

        private void VariableStore_VariableChanged(object sender, VariableChangedEventArgs arg)
        {
            Renderer.SetValue(arg.Name, arg.Value);
            RuleContainer.ApplyRules(VariableStore, Renderer);
        }

    }
}
