using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLIdentifier : IQLExpression
    {
        public QLIdentifier(string name)
        {
            this.Name = name;
        }

        public string Name
        {
            get;
        }
        
        public QLType? GetQLType()
        {
            // TODO: See how to handle this. Pass set of defined variables into type funcion?
            throw new NotImplementedException();
        }
    }
}
