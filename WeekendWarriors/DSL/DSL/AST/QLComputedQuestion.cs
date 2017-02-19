using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLComputedQuestion : INode
    {
        public QLComputedQuestion(INode question, INode expression)
        {
            this.Question = question;
            this.Expression = expression;
        }

        public INode Question
        {
            get;
        }

        public INode Expression
        {
            get;
        }
    }
}
