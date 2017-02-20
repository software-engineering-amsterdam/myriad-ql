using Questionnaires.AST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis
{
    class LiteralValidator: Validator
    {
        public QLType Evaluate(QLMoney literal)
        {
            string errorString;

            try
            {
                decimal.Parse(literal.StringValue);
                return QLType.Money;
            }
            catch (FormatException)
            {
                errorString = string.Format("Format exception while converting literal \"{0}\" to money", literal.StringValue);
            }
            catch (OverflowException)
            {
                errorString = string.Format("Overflow exception while converting literal \"{0}\" to money", literal.StringValue);
            }
            catch (Exception e)
            {
                errorString = string.Format("Cannot convert literal \"{0}\" to money \"{1}\"", literal.StringValue, e.Message);
            }
            OnInvalidExpression(new InvalidExpressionEventArgs(errorString));
            return QLType.None;
        }

        public QLType Evaluate(QLNumber literal)
        {
            string errorString;

            try
            {
                int.Parse(literal.StringValue);
                return QLType.Number;
            }
            catch (FormatException)
            {
                errorString = string.Format("Format exception while converting literal \"{0}\" to int", literal.StringValue);
            }
            catch (OverflowException)
            {
                errorString = string.Format("Overflow exception while converting literal \"{0}\" to int", literal.StringValue);
            }
            catch (Exception e)
            {
                errorString = string.Format("Cannot convert literal \"{0}\" to int \"{1}\"", literal.StringValue, e.Message);
            }

            OnInvalidExpression(new InvalidExpressionEventArgs(errorString));
            return QLType.None;
        }
    }
}
