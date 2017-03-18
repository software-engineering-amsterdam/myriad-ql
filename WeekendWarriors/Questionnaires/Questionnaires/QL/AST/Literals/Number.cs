using Questionnaires.Compilation;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;
using System;
using System.Collections.Generic;

namespace Questionnaires.QL.AST.Literals
{
    public class Number : IExpression
    {
        public string ValueAsString { get; }
        public int Value { get { return int.Parse(ValueAsString); } }

        public Number(string value)
        {
            ValueAsString = value;
        }
        
        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            try
            {
                var val = Value;
                return true;
            }
            catch (FormatException)
            {
                messages.Add(new Error(string.Format("Cannot convert literal {0} to number", ValueAsString)));
            }
            catch (OverflowException)
            {
                messages.Add(new Error(string.Format("Value {0} is too large for number variable", ValueAsString)));
            }
            return false;
        }

        public IType GetResultType(QLContext context)
        {
            return new IntegerType();
        }
    }
}
