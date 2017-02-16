using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
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

    class QLBinaryOperation : INode
    {
        protected QLBinaryOperation(INode lhs, QLBinaryOperator operation, INode rhs)
        {
            this.Lhs = lhs;
            this.Operator = operation;
            this.Rhs = rhs;
        }

        public INode Lhs
        {
            get;
        }

        public QLBinaryOperator Operator
        {
            get;
        }

        public INode Rhs
        {
            get;
        }
    }
}
