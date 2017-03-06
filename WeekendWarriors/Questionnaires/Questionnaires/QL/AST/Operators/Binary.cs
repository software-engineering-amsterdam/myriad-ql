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
    public abstract class Binary : IExpression
    {
        protected Binary(IExpression lhs, IExpression rhs)
        {
            this.Lhs = lhs;
            this.Rhs = rhs;
        }

        public IExpression Lhs
        {
            get;
        }

        public IExpression Rhs
        {
            get;
        }
        
        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            // check the two child nodes have semantic errors
            var lhsSemanticsOk = Lhs.CheckSemantics(context, messages);
            var rhsSemanticsOk = Rhs.CheckSemantics(context, messages);

            // Don't continue with semantics checking if one of the child nodes has an error
            if (!lhsSemanticsOk || !rhsSemanticsOk)
                return false;

            try
            {
                // Call the child class to get the return type of this epxression if the operation is not supported this will throw
                var resultType = GetResultType(context);
                return true;
            }
            catch (NotSupportedException)
            {
                messages.Add(new Error(string.Format("Cannot apply operator {0} on arguments of type {1} and {2}", this, Lhs.GetResultType(context), Rhs.GetResultType(context))));
                return false;
            }
            catch(DivideByZeroException)
            {
                return true;
            }
        }

        public abstract IType GetResultType(QLContext context);
    }
}
