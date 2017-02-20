using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public interface IQLExpression : INode
    {
        QLType? GetQLType();
    }
}
