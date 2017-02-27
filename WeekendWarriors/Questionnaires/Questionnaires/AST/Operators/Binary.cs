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

    public abstract class Binary : IExpression
    {
        protected Binary(IExpression lhs, QLBinaryOperator operation, IExpression rhs)
        {
            this.Lhs = lhs;
            this.Operator = operation;
            this.Rhs = rhs;
        }

        public IExpression Lhs
        {
            get;
        }

        public QLBinaryOperator Operator
        {
            get;
        }

        public IExpression Rhs
        {
            get;
        }

        public abstract QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events);
    }
}
