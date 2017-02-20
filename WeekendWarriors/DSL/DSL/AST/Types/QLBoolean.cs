using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLBoolean : IQLExpression
    {
        public QLBoolean(bool value)
        {
            this.Value = value;
        }

        public bool Value
        {
            get;
        }

        public bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            return true;
        }

        QLType? IQLExpression.GetQLType()
        {
            return QLType.Bool;
        }
    }
}
