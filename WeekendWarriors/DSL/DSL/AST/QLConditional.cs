using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    class QLConditional : INode
    {
        public QLConditional(INode condition, INode thenStatement, INode elseStatements)
        {
            this.Condition = condition;
            this.ThenStatements = thenStatement;
            this.ElseStatements = elseStatements;
        }

        public INode Condition
        {
            get;
        }

        public INode ThenStatements
        {
            get;
            set;
        }

        public INode ElseStatements
        {
            get;
            set;
        }

        public string ToString(uint depth)
        {
            string toPrint = "";

            for (int i = 0; i < depth; i++)
                toPrint += "\t";

            toPrint += "Conditional:\n\r" + Condition.ToString(depth + 1) + ThenStatements.ToString(depth + 1);
            if(ElseStatements != null)
                toPrint += ElseStatements.ToString(depth + 1);

            return toPrint;
        }
    }
}
