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

        ReturnType Visit(QLAndOperation node);
        ReturnType Visit(QLOrOperation node);
        ReturnType Visit(QLAdditionOperation node);
        ReturnType Visit(QLSubtractionOperation node);
        ReturnType Visit(QLDivisionOperation node);
        ReturnType Visit(QLMultiplyOperation node);
        ReturnType Visit(QLGreaterThanOperation node);
        ReturnType Visit(QLGreaterThanOrEqualOperation node);
        ReturnType Visit(QLLessThanOperation node);
        ReturnType Visit(QLLessThanOrEqualOperation node);
        ReturnType Visit(QLEqualityOperation node);
        ReturnType Visit(QLInequalOperation node);        
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

        void Visit(QLAndOperation node);
        void Visit(QLOrOperation node);
        void Visit(QLAdditionOperation node);
        void Visit(QLSubtractionOperation node);
        void Visit(QLDivisionOperation node);
        void Visit(QLMultiplyOperation node);
        void Visit(QLGreaterThanOperation node);
        void Visit(QLGreaterThanOrEqualOperation node);
        void Visit(QLLessThanOperation node);
        void Visit(QLLessThanOrEqualOperation node);
        void Visit(QLEqualityOperation node);
        void Visit(QLInequalOperation node);
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
