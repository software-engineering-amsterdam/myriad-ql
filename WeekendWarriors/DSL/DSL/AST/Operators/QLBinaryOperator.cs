using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public enum QLOperator
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

    class QLBinaryOperator : INode
    {
        protected QLBinaryOperator(INode lhs, QLOperator operation, INode rhs)
        {
            this.Lhs = lhs;
            this.Operator = operation;
            this.Rhs = rhs;
        }

        public INode Lhs
        {
            get;
        }

        public QLOperator Operator
        {
            get;
        }

        public INode Rhs
        {
            get;
        }
    }
}
