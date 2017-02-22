using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLComputedQuestion : INode
    {
        public QLComputedQuestion(QLQuestion question, IQLExpression expression)
        {
            this.Question = question;
            this.Expression = expression;
        }

        public QLQuestion Question
        {
            get;
        }

        public IQLExpression Expression
        {
            get;
        }

        public bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            bool questionValid = Question.Validate(ref warnings, ref errors);
            bool ExpressionValid = Expression.Validate(ref warnings, ref errors);

            if (!questionValid || !ExpressionValid)
                return false;

            // Computed question is like an assignment. Only valid when question type is
            // equal to the expression type
            var questionType = Question.Type;
            var expressionType = Expression.GetQLType();

            Debug.Assert(expressionType.HasValue);

            if (questionType != expressionType)
            {
                errors.Add(string.Format("Cannot assign expression with type {0} to question of type {1}", expressionType, questionType));
                return false;
            }

            return true;
        }
    }
}
