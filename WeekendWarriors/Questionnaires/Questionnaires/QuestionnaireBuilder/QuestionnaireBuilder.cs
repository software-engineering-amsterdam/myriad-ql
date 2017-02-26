using Questionnaires.AST.Visitor;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.AST;
using Questionnaires.AST.Operators;
using Questionnaires.Rule;
using Questionnaires.Renderer;
using Questionnaires.VariableStore;
using Questionnaires.Value;

namespace Questionnaires.QuestionaireBuilder
{
    class QuestionnaireBuilder : IASTVisitor<Func<IValue>>
    {
        private VariableStore.VariableStore VariableStore;
        private Renderer.Renderer Renderer;
        private RuleContainer.RuleContainer RuleContainer;
        private List<Func<bool>> QuestionRules; // I don't really likes this
        private List<Func<IValue>> QuestionResult;

        public QuestionnaireBuilder(VariableStore.VariableStore variableStore, Renderer.Renderer renderer, RuleContainer.RuleContainer ruleContainer)
        {
            VariableStore = variableStore;
            Renderer = renderer;
            RuleContainer = ruleContainer;
        }

        public Func<IValue> Visit(QLComputedQuestion node)
        {
            // Add it as a question
            Visit(node.Question);
            QuestionRules = new List<Func<bool>>();
            QuestionResult = new List<Func<IValue>>();
            Visit((dynamic)node.Expression);

            // Now we add a rule for the visibility and the value
            var rule = new Rule.Rule(
                (VariableStore.IVariableStore variableStore, Renderer.Renderer renderer) =>
                {
                    // We display a computed question if all of the identifiers in the expression hold a value
                    var computedRules = QuestionRules;

                    bool visibility = true;
                    foreach (var computedRule in computedRules)
                    {
                        if (!computedRule.Invoke())
                        {
                            visibility = false;
                        }
                    }
                    renderer.SetVisibility(
                        node.Question.Identifier,
                        visibility ? Question.Visibility.Visible : Question.Visibility.Hidden
                    );
                    if (!visibility)
                    {
                        // if not visible remove from store so we don't get destroyed by conditionals
                        try
                        {
                            VariableStore.RemoveValue(node.Question.Identifier);
                        }
                        catch (Exception)
                        { }
                        // return;
                    }
                    else
                    {
                        // Now we set the value based on the computation
                        switch (node.Question.Type)
                        {
                            case QLType.Bool:
                                VariableStore.SetValue(node.Question.Identifier, QuestionResult[0].Invoke().AsBool());
                                Renderer.SetValue(node.Question.Identifier, QuestionResult[0].Invoke());
                                break;
                            case QLType.Money:
                                break;
                            case QLType.Number:
                                break;
                            case QLType.String:
                                break;
                            default://\todo: What do we do here...
                                throw new Exception("fk");
                        }
                    }
                }
            );
            RuleContainer.AddRule(rule);
            return null;
        }

        public Func<IValue> Visit(QLBinaryOperation node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLComparisonOperation node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLLogicalOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return (() => { return new BoolValue(lhsFunc().asBool() && rhsFunc().asBool()); });
        }

        public Func<IValue> Visit(QLPositiveOperation node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLBangOperation node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLMoney node)
        {
            return () => { return new DecimalValue(node.Value); };
        }

        public Func<IValue> Visit(QLString node)
        {
            return () => { return new StringValue(node.Value); };
        }

        public Func<IValue> Visit(QLIdentifier node)
        {
            return () => { return VariableStore.GetValue(node.Name); };
        }

        public Func<IValue> Visit(QLNumber node)
        {
            return () => { return new IntValue(node.Value); };
        }

        public Func<IValue> Visit(QLBoolean node)
        {
            return () => { return new BoolValue(node.Value); };
        }

        public Func<IValue> Visit(QLNegativeOperation node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLUnaryOperation node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLEqualityOperation node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLArithmeticOperation node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLConditional node)
        {
            throw new NotImplementedException();
        }

        public Func<IValue> Visit(QLQuestion node)
        {
            // Renderer needs an IQuestion so we build that
            var question = new Question.Question();
            question.Body = node.Body;
            question.Name = node.Identifier;

            // We gotta convert the enums here, also we gotta assign default values
            question.Type = (Question.QuestionType)node.Type; // This is a hack to cast because they have the same indexes
            switch (node.Type)
            {
                case QLType.Bool:
                    VariableStore.SetValue(question.Name, false);
                    break;
                case QLType.Money:
                    VariableStore.SetValue(question.Name, 0.0m);
                    break;
                case QLType.Number:
                    VariableStore.SetValue(question.Name, 0);
                    break;
                case QLType.String:
                    VariableStore.SetValue(question.Name, "");
                    break;
                default://\todo: What do we do here...
                    throw new Exception("fk");
            }

            Renderer.AddQuestion(question);
            return null;
        }

        public Func<IValue> Visit(QLForm node)
        {
            foreach (var statement in node.Statements)
            {
                Visit((dynamic)statement);
            }

            // For a form all we want to do is just change the window title
            // you cannot change this later through user input
            Renderer.SetWindowTitle(node.Identifier);
            return null;
        }
    }
}
