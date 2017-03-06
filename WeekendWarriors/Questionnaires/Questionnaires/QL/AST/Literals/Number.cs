using System;
using System.Collections.Generic;
using Questionnaires.SemanticAnalysis.Messages;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Literals
{
    public class Number : IExpression
    {
        public Number(string value)
        {
            this.StringType = value;
        }

        public string StringType { get; }        

        public int Value { get { return int.Parse(StringType); } }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            try
            {                
                var val = Value;
                return true;
            }
            catch (FormatException)
            {
                messages.Add(new Error(string.Format("Cannot convert literal {0} to number", StringType)));
            }
            catch (OverflowException)
            {
                messages.Add(new Error(string.Format("Value {0} is too large for number variable", StringType)));
            }
            return false;
        }

        public IType GetResultType(QLContext context)
        {
            return new IntegerType();
        }
    }
}
