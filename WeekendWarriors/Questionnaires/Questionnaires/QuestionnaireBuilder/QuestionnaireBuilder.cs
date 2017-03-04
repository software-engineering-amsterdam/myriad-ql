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
    class QuestionnaireBuilder : IASTVisitor<Func<IValue>>
    {        
        private VariableStore.VariableStore VariableStore;
        private Renderer.Renderer Renderer;
        private RuleContainer.RuleContainer RuleContainer;

        public QuestionnaireBuilder(VariableStore.VariableStore variableStore, Renderer.Renderer renderer, RuleContainer.RuleContainer ruleContainer)
        {
            VariableStore = variableStore;
            Renderer = renderer;
            RuleContainer = ruleContainer;

            // Connect runtime components
            VariableStore.VariableChanged += VariableStore_VariableChanged;
        }

        private void VariableStore_VariableChanged(object sender, VariableChangedEventArgs arg)
        {
            Renderer.SetValue(arg.Name, arg.Value);
            RuleContainer.ApplyRules(VariableStore, Renderer);
        }

        public Func<IValue> Visit(ComputedQuestion node)
        {
            Func<IValue> questionFunction = Visit(node.Question);
            Func<IValue> expressionFunction = Visit((dynamic)node.Expression);

            VariableStore.SetValue(node.Question.Identifier, expressionFunction());            

            RuleContainer.AddRule(
                new Action<IVariableStore, Renderer.Renderer>((variableStore, renderer) =>
                {
                    variableStore.SetValue(node.Question.Identifier, expressionFunction());
                }));
            
            return questionFunction;
        }
        
        public Func<IValue> Visit(Positive node)
        {
            return Visit((dynamic)node.Operand);
        }

        public Func<IValue> Visit(Negative node)
        {
            var lhsFunc = Visit((dynamic)node.Operand);
            return () =>
            {
                try
                {
                    return new IntValue(-lhsFunc().AsInt());
                }
                catch (NotSupportedException)
                {
                    return new DecimalValue(-lhsFunc().AsDecimal());
                }
            };
        }

        public Func<IValue> Visit(Bang node)
        {
            var lhsFunc = Visit((dynamic)node.Operand);
            return () => 
            {
                return new BoolValue(!lhsFunc().AsBool());
            };
        }

        public Func<IValue> Visit(AST.Literals.Money node)
        {
            return () => { return new DecimalValue(node.Value); };
        }

        public Func<IValue> Visit(AST.Literals.String node)
        {
            return () => { return new StringValue(node.Value); };
        }

        public Func<IValue> Visit(Identifier node)
        {
            return () => { return VariableStore.GetValue(node.Name); };
        }

        public Func<IValue> Visit(AST.Literals.Number node)
        {
            return () => { return new IntValue(node.Value); };
        }

        public Func<IValue> Visit(AST.Literals.Boolean node)
        {
            return () => { return new BoolValue(node.Value); };
        }
        
        public Func<IValue> Visit(AST.Question node)
        {
            // Renderer needs an IQuestion so we build that
            var question = new Question.Question();
            question.Body = node.Body;
            question.Name = node.Identifier;
            question.Value = node.Type;

            Renderer.AddQuestion((dynamic)question.Value, question);
            VariableStore.SetValue(question.Name, question.Value);
          
            return () => { return new StringValue(node.Identifier); };
        }

        public Func<IValue> Visit(Form node)
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

        public Func<IValue> Visit(And node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return new BoolValue(lhsFunc().And(rhsFunc()));
            };
        }

        public Func<IValue> Visit(Or node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return new BoolValue(lhsFunc().Or(rhsFunc()));
            };
        }

        public Func<IValue> Visit(Addition node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().Add((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(Subtraction node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().Subtract((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(Division node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().Divide((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(Multiply node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().Multiply((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(GreaterThan node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().GreaterThan((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(GreaterThanOrEqual node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().GreaterThanOrEqual((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(LessThan node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().LessThan((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(LessThanOrEqual node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().LessThanOrEqual((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(Inequal node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().InequalTo((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(Conditional node)
        {
            Func<IValue> conditionFunction = Visit((dynamic)node.Condition);
            
            foreach (var thenStatement in node.ThenStatements)
            {
                Func<IValue> thenFunction = Visit((dynamic)thenStatement);
                if (thenFunction == null)
                    continue;

                RuleContainer.AddRule(
                    new Action<IVariableStore, Renderer.Renderer>((variableStore, renderer) =>
                    {
                        if (conditionFunction().AsBool())
                        {
                            renderer.SetVisibility(thenFunction().AsString(), true);
                        }
                        else
                        {
                            renderer.SetVisibility(thenFunction().AsString(), false);
                        }
                    })
                );
            }

            foreach (var elseStatement in node.ElseStatements)
            {
                Func<IValue> elseFunction = Visit((dynamic)elseStatement);
                if (elseFunction == null)
                    continue;

                RuleContainer.AddRule(
                    new Action<IVariableStore, Renderer.Renderer>((variableStore, renderer) =>
                    {
                        if (!conditionFunction().AsBool())
                        {
                            renderer.SetVisibility(elseFunction().AsString(), true);
                        }
                        else
                        {
                            renderer.SetVisibility(elseFunction().AsString(), false);
                        }
                    })
                );
            }

            return null;
        }

        public Func<IValue> Visit(Equal node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().EqualTo((dynamic)rhsFunc());
            };
        }
    }
}
