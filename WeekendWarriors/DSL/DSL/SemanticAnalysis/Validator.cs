using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.SemanticAnalysis
{
    class Validator
    {
        public delegate void InvalidExpressionEventHandler(object sender, InvalidExpressionEventArgs e);
        public event InvalidExpressionEventHandler InvalidExpression;

        protected virtual void OnInvalidExpression(InvalidExpressionEventArgs e)
        {
            if (InvalidExpression != null)
                InvalidExpression(this, e);
        }
    }
}
