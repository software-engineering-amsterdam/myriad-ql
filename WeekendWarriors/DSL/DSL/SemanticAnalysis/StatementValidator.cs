using DSL.AST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.SemanticAnalysis
{
    class StatementValidator
    {
        private readonly QLType[] ConditionTypes = new QLType[] { QLType.Bool };

        public QLType Evaluate(QLQuestion expression)
        {
            // Nothing to validate 
            return expression.Type;
        }

        public QLType Evaluate(QLComputedQuestion expression, QLType lhsType, QLType rhsType)
        {
            if (lhsType != rhsType)
            {
                OnInvalidExpression(new InvalidExpressionEventArgs("cannot assign " + rhsType.ToString() + " to question with type " + lhsType.ToString()));
            }
            return QLType.None;
        }

        public QLType Evaluate(QLConditional expression, QLType conditionType)
        {
            if (!ConditionTypes.Contains(conditionType))
            {
                string errorString = string.Format("cannot apply the conditional operator on type <{0}>",
                    conditionType);

                OnInvalidExpression(new InvalidExpressionEventArgs(errorString));
            }

            return QLType.None;
        }

        public delegate void InvalidExpressionEventHandler(object sender, InvalidExpressionEventArgs e);
        public event InvalidExpressionEventHandler InvalidExpression;

        protected virtual void OnInvalidExpression(InvalidExpressionEventArgs e)
        {
            if (InvalidExpression != null)
                InvalidExpression(this, e);
        }
    }
}
