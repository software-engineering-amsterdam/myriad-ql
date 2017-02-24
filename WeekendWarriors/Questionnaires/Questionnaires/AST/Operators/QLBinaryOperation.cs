using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;

namespace Questionnaires.AST
{
    public enum QLBinaryOperator
    {
        And,
        Or,
        Addition,
        Subtraction,
        Division,
        Multiplication,
        GreaterThan,
        GreaterThanOrEqual,
        LessThan,
        LessThanOrEqual,
        Equal,
        Inequal
    };

    public abstract class QLBinaryOperation : IQLExpression
    {
        protected QLBinaryOperation(IQLExpression lhs, QLBinaryOperator operation, IQLExpression rhs)
        {
            this.Lhs = lhs;
            this.Operator = operation;
            this.Rhs = rhs;
        }

        public IQLExpression Lhs
        {
            get;
        }

        public QLBinaryOperator Operator
        {
            get;
        }

        public IQLExpression Rhs
        {
            get;
        }

        public abstract QLType? CheckTypes(List<QLType> parameters, QLContext context, List<ISemenaticAnalysisEvent> events);
    }
}
