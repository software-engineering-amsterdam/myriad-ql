using Questionnaires.AST.Operators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST.Visitor
{
    public interface IASTVisitor<ReturnType>
    {
        ReturnType Visit(QLForm node);
        ReturnType Visit(QLQuestion node);
        ReturnType Visit(QLComputedQuestion node);
        ReturnType Visit(QLConditional node);
        ReturnType Visit(QLBinaryOperation node);
        ReturnType Visit(QLArithmeticOperation node);
        ReturnType Visit(QLComparisonOperation node);
        ReturnType Visit(QLEqualityOperation node);
        ReturnType Visit(QLLogicalOperation node);
        ReturnType Visit(QLUnaryOperation node);
        ReturnType Visit(QLPositiveOperation node);
        ReturnType Visit(QLNegativeOperation node);
        ReturnType Visit(QLBangOperation node);
        ReturnType Visit(QLBoolean node);
        ReturnType Visit(QLMoney node);
        ReturnType Visit(QLNumber node);
        ReturnType Visit(QLString node);
        ReturnType Visit(QLIdentifier node);
    }
}
