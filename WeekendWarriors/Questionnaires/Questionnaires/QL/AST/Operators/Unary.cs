using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.SemanticAnalysis.Messages;
using Questionnaires.Value;

namespace Questionnaires.QL.AST
{
    public abstract class Unary : IExpression
    {
        public Unary(IExpression operand)
        {
            this.Operand = operand;
        }

        public IExpression Operand
        {
            get;
        }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            if (!Operand.CheckSemantics(context, messages))
                return false;

            try
            {
                // Call the child class to get the return type of this epxression if the operation is not supported this will throw
                GetResultType(context);
                return true;
            }
            catch (NotSupportedException)
            {
                messages.Add(new Error(string.Format("Cannot apply {0} operator on type {0}", this, Operand.GetResultType(context))));
                return false;
            }
        }
        public abstract IValue GetResultType(QLContext context);
    }
}
