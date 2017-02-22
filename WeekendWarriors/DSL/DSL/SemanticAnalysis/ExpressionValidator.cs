using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.AST;
using DSL.AST.Operators;
using System.ComponentModel;

namespace DSL.SemanticAnalysis
{
    /* TODO: I don't like the fact that we are passing in objects only to make overloading work, but I'm not sure how to fix this without
     * Either adding an enumeration of QLNodes or having a method for each QLNode type. Both of those seem worse than this... */

    class ExpressionValidator: Validator
    {
        /* +, -, *, / */
        private readonly QLType[] ArithmaticTypes = new QLType[] { QLType.Number, QLType.Money };
        /* <, <=, >, >= */
        private readonly QLType[] ComparableTypes = new QLType[] { QLType.Number, QLType.Money };
        /* ! */
        private readonly QLType[] bangTypes = new QLType[] { QLType.Bool };
        /* +, - */
        private readonly QLType[] signTypes = new QLType[] { QLType.Number, QLType.Money };
        
        public QLType Evaluate(QLArithmeticOperation expression, QLType lhsType, QLType rhsType)
        {
            /* As it is not specified in the assignment, we keep it simple and only allow aritmatic between two 
             * equal types */
            if (lhsType != rhsType)
            {
                string errorString = string.Format("cannot apply operator <{0}> to different types ({1} and {2})",
                    expression.Operator,
                    lhsType,
                    rhsType);

                OnInvalidExpression(new InvalidExpressionEventArgs(errorString));

                return QLType.None;
            }

            if (!ArithmaticTypes.Contains(lhsType))
            {
                string errorString = string.Format("cannot apply operator <{0}> to type {1}",
                    expression.Operator,
                    lhsType);

                OnInvalidExpression(new InvalidExpressionEventArgs(errorString));

                return QLType.None;
            }
            return lhsType;
        }

        public QLType Evaluate(QLComparisonOperation expression, QLType lhsType, QLType rhsType)
        {
            if (lhsType != rhsType)
            {
                string errorString = string.Format("cannot compare different types ({0} and {1})",
                    lhsType,
                    rhsType);

                OnInvalidExpression(new InvalidExpressionEventArgs(errorString));

                return QLType.None;
            }

            if (!ComparableTypes.Contains(lhsType))
            {
                string errorString = string.Format("cannot apply operator <{0}> to type {1}",
                    expression.Operator,
                    lhsType);

                OnInvalidExpression(new InvalidExpressionEventArgs(errorString));

                return QLType.None;
            }

            return QLType.Bool;
        }

        public QLType Evaluate(QLEqualityOperation expression, QLType lhsType, QLType rhsType)
        {
            if (lhsType != rhsType)
            {
                string errorString = string.Format("cannot check different types for equality ({0} and {1})",
                    lhsType,
                    rhsType);

                OnInvalidExpression(new InvalidExpressionEventArgs(errorString));

                return QLType.None;
            }

            return lhsType;
        }

        public QLType Evaluate(QLLogicalOperation expression, QLType lhsType, QLType rhsType)
        {
            if(lhsType != QLType.Bool || rhsType != QLType.Bool)
            {
                string errorString = string.Format("cannot apply logical operator {0} to types other than boolean ({1} and {2})",
                    expression.Operator,
                    lhsType,
                    rhsType);

                OnInvalidExpression(new InvalidExpressionEventArgs(errorString));

                return QLType.None;
            }

            return QLType.Bool;
        }

        public QLType Evaluate(QLUnaryOperation expression, QLType operandType)
        {
            //QLType[] allowedTypes = null;

            //switch (expression.Operator)
            //{
            //    case QLUnaryOperator.Bang:
            //        allowedTypes = bangTypes;
            //        break;
            //    case QLUnaryOperator.Plus:                                        
            //    case QLUnaryOperator.Minus:
            //        allowedTypes = signTypes;
            //        break;
            //    default:
            //        throw new InvalidEnumArgumentException();
            //}

            //if (!allowedTypes.Contains(operandType))
            //{
            //    string errorString = string.Format("cannot apply operator <{0}> to type {1}",
            //        expression.Operator,
            //        operandType);

            //    OnInvalidExpression(new InvalidExpressionEventArgs(errorString));

            //    return QLType.None;
            //}

            //return operandType;
            return QLType.None;
        }
    }
}
