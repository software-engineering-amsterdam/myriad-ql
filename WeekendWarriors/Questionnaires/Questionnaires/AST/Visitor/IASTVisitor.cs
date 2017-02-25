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

    // TODO: Passing in void as a generic parameter is not allowed. Apparently creating a second non-generic 
    // interface is the standard solution the .NET framework uses
    public interface IASTVisitor
    {
        void Visit(QLForm node);
        void Visit(QLQuestion node);
        void Visit(QLComputedQuestion node);
        void Visit(QLConditional node);
        void Visit(QLBinaryOperation node);
        void Visit(QLArithmeticOperation node);
        void Visit(QLComparisonOperation node);
        void Visit(QLEqualityOperation node);
        void Visit(QLLogicalOperation node);
        void Visit(QLUnaryOperation node);
        void Visit(QLPositiveOperation node);
        void Visit(QLNegativeOperation node);
        void Visit(QLBangOperation node);
        void Visit(QLBoolean node);
        void Visit(QLMoney node);
        void Visit(QLNumber node);
        void Visit(QLString node);
        void Visit(QLIdentifier node);
    }
}
