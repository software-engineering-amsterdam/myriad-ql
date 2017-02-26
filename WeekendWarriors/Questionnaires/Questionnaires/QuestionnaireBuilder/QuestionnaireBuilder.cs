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

        public Func<IValue> Visit(QLComputedQuestion node)
        {
            Func<IValue> questionFunction = Visit(node.Question);
            Func<IValue> expressionFunction = Visit((dynamic)node.Expression);

            switch (node.Question.Type)
            {
                case QLType.Bool:
                    VariableStore.SetValue(node.Question.Identifier, expressionFunction());
                    break;
                case QLType.Money:
                    VariableStore.SetValue(node.Question.Identifier, expressionFunction());
                    break;
                case QLType.Number:
                    VariableStore.SetValue(node.Question.Identifier, expressionFunction());
                    break;
                case QLType.String:
                    VariableStore.SetValue(node.Question.Identifier, expressionFunction());
                    break;
                default://\todo: What do we do here...
                    throw new Exception("fk");
            }

            RuleContainer.AddRule(
                new Rule.Rule((variableStore, renderer) =>
                {
                    variableStore.SetValue(node.Question.Identifier, expressionFunction());
                }));
            
            return questionFunction;
        }
        
        public Func<IValue> Visit(QLPositiveOperation node)
        {
            return Visit((dynamic)node.Operand);
        }

        public Func<IValue> Visit(QLNegativeOperation node)
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

        public Func<IValue> Visit(QLBangOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Operand);
            return () => 
            {
                return new BoolValue(!lhsFunc().AsBool());
            };
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
        
        public Func<IValue> Visit(QLQuestion node)
        {
            // Renderer needs an IQuestion so we build that
            var question = new Question.Question();
            question.Body = node.Body;
            question.Name = node.Identifier;

            // We gotta convert the enums here, also we gotta assign default values
            question.Type = (Question.QuestionType)node.Type; // This is a hack to cast because they have the same indexes
            Renderer.AddQuestion(question);

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
            return () => { return new StringValue(node.Identifier); };
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

        public Func<IValue> Visit(QLAndOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return new BoolValue(lhsFunc().And(rhsFunc()));
            };
        }

        public Func<IValue> Visit(QLOrOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return new BoolValue(lhsFunc().Or(rhsFunc()));
            };
        }

        public Func<IValue> Visit(QLAdditionOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().Add((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLSubtractionOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().Subtract((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLDivisionOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().Divide((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLMultiplyOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().Multiply((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLGreaterThanOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().GreaterThan((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLGreaterThanOrEqualOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().GreaterThanOrEqual((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLLessThanOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().LessThan((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLLessThanOrEqualOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().LessThanOrEqual((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLInequalOperation node)
        {
            var lhsFunc = Visit((dynamic)node.Lhs);
            var rhsFunc = Visit((dynamic)node.Rhs);
            return () =>
            {
                return lhsFunc().InequalTo((dynamic)rhsFunc());
            };
        }

        public Func<IValue> Visit(QLConditional node)
        {
            Func<IValue> conditionFunction = Visit((dynamic)node.Condition);
            
            foreach (var thenStatement in node.ThenStatements)
            {
                Func<IValue> thenFunction = Visit((dynamic)thenStatement);
                if (thenFunction == null)
                    continue;

                RuleContainer.AddRule(
                    new Rule.Rule((variableStore, renderer) =>
                    {
                        if (conditionFunction().AsBool())
                        {
                            renderer.SetVisibility(thenFunction().AsString(), Question.Visibility.Visible);
                        }
                        else
                        {
                            renderer.SetVisibility(thenFunction().AsString(), Question.Visibility.Hidden);
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
                    new Rule.Rule((variableStore, renderer) =>
                    {
                        if (!conditionFunction().AsBool())
                        {
                            renderer.SetVisibility(elseFunction().AsString(), Question.Visibility.Visible);
                        }
                        else
                        {
                            renderer.SetVisibility(elseFunction().AsString(), Question.Visibility.Hidden);
                        }
                    })
                );
            }

            return null;
        }

        public Func<IValue> Visit(QLEqualOperation node)
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
