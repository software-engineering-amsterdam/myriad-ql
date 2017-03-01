//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Text;
//using System.Threading.Tasks;

//namespace Questionnaires.ExpressionEvaluator
//{
//    class Evaluator
//    {
//        protected VariableStore.VariableStore VarialbeStore;

//        public Evaluator(VariableStore.VariableStore store)
//        {
//            this.VarialbeStore = store;
//        }

//        public Value.IValue Evaluate(AST.IExpression expression)
//        {
//            return new Value.BoolValue(false);
//        }

//        private Value.IValue Visit(AST.Operators.Positive node)
//        {
//            return Visit((dynamic)node.Operand);
//        }

//        public Value.IValue Visit(AST.Operators.Negative node)
//        {
//            var lhsFunc = Visit((dynamic)node.Operand);
//            return () =>
//            {
//                try
//                {
//                    return new IntValue(-lhsFunc().AsInt());
//                }
//                catch (NotSupportedException)
//                {
//                    return new DecimalValue(-lhsFunc().AsDecimal());
//                }
//            };
//        }

//        public Value.IValue Visit(AST.Operators.Bang node)
//        {
//            var lhsFunc = Visit((dynamic)node.Operand);
//            return () =>
//            {
//                return new BoolValue(!lhsFunc().AsBool());
//            };
//        }

//        public Value.IValue Visit(AST.Literals.Money node)
//        {
//            return () => { return new DecimalValue(node.Value); };
//        }

//        public Value.IValue Visit(AST.Literals.String node)
//        {
//            return () => { return new StringValue(node.Value); };
//        }

//        public Value.IValue Visit(AST.Operators.Identifier node)
//        {
//            return () => { return VariableStore.GetValue(node.Name); };
//        }

//        public Value.IValue Visit(AST.Literals.Number node)
//        {
//            return () => { return new IntValue(node.Value); };
//        }

//        public Value.IValue Visit(AST.Literals.Boolean node)
//        {
//            return () => { return new BoolValue(node.Value); };
//        }

//        public Value.IValue Visit(AST.Question node)
//        {
//            // Renderer needs an IQuestion so we build that
//            var question = new Question.Question();
//            question.Body = node.Body;
//            question.Name = node.Identifier;

//            // We gotta convert the enums here, also we gotta assign default values
//            question.Type = (Question.QuestionType)node.Type; // This is a hack to cast because they have the same indexes
//            Renderer.AddQuestion(question);

//            switch (node.Type)
//            {
//                case QLType.Bool:
//                    VariableStore.SetValue(question.Name, false);
//                    break;
//                case QLType.Money:
//                    VariableStore.SetValue(question.Name, 0.0m);
//                    break;
//                case QLType.Number:
//                    VariableStore.SetValue(question.Name, 0);
//                    break;
//                case QLType.String:
//                    VariableStore.SetValue(question.Name, "");
//                    break;
//                default:
//                    throw new InvalidEnumArgumentException();
//            }
//            return () => { return new StringValue(node.Identifier); };
//        }

//        public Value.IValue Visit(Form node)
//        {
//            foreach (var statement in node.Statements)
//            {
//                Visit((dynamic)statement);
//            }

//            // For a form all we want to do is just change the window title
//            // you cannot change this later through user input
//            Renderer.SetWindowTitle(node.Identifier);



//            return null;
//        }

//        public Value.IValue Visit(And node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return new BoolValue(lhsFunc().And(rhsFunc()));
//            };
//        }

//        public Value.IValue Visit(Or node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return new BoolValue(lhsFunc().Or(rhsFunc()));
//            };
//        }

//        public Value.IValue Visit(Addition node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().Add((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(Subtraction node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().Subtract((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(Division node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().Divide((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(Multiply node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().Multiply((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(GreaterThan node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().GreaterThan((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(GreaterThanOrEqual node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().GreaterThanOrEqual((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(LessThan node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().LessThan((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(LessThanOrEqual node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().LessThanOrEqual((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(Inequal node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().InequalTo((dynamic)rhsFunc());
//            };
//        }

//        public Value.IValue Visit(Conditional node)
//        {
//            Value.IValue conditionFunction = Visit((dynamic)node.Condition);

//            foreach (var thenStatement in node.ThenStatements)
//            {
//                Value.IValue thenFunction = Visit((dynamic)thenStatement);
//                if (thenFunction == null)
//                    continue;

//                RuleContainer.AddRule(
//                    new Rule.Rule((variableStore, renderer) =>
//                    {
//                        if (conditionFunction().AsBool())
//                        {
//                            renderer.SetVisibility(thenFunction().AsString(), Question.Visibility.Visible);
//                        }
//                        else
//                        {
//                            renderer.SetVisibility(thenFunction().AsString(), Question.Visibility.Hidden);
//                        }
//                    })
//                );
//            }

//            foreach (var elseStatement in node.ElseStatements)
//            {
//                Value.IValue elseFunction = Visit((dynamic)elseStatement);
//                if (elseFunction == null)
//                    continue;

//                RuleContainer.AddRule(
//                    new Rule.Rule((variableStore, renderer) =>
//                    {
//                        if (!conditionFunction().AsBool())
//                        {
//                            renderer.SetVisibility(elseFunction().AsString(), Question.Visibility.Visible);
//                        }
//                        else
//                        {
//                            renderer.SetVisibility(elseFunction().AsString(), Question.Visibility.Hidden);
//                        }
//                    })
//                );
//            }

//            return null;
//        }

//        public Value.IValue Visit(Equal node)
//        {
//            var lhsFunc = Visit((dynamic)node.Lhs);
//            var rhsFunc = Visit((dynamic)node.Rhs);
//            return () =>
//            {
//                return lhsFunc().EqualTo((dynamic)rhsFunc());
//            };
//        }
//    }
//}
