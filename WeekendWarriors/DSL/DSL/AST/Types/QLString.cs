using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLString : IQLExpression
    {
        public QLString(string value)
        {
            this.Value = value;
        }

        public string Value
        {
            get;
        }

        public bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            return true;
        }

        QLType? IQLExpression.GetQLType()
        {
            return QLType.String;
        }
    }
}
