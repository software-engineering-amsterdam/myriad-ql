using Questionnaires.QL.AST.Operators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QL.AST.Visitor
{
    public interface IASTVisitor<ReturnType>
    {
        ReturnType Visit(Form node);
        ReturnType Visit(Question node);
        ReturnType Visit(ComputedQuestion node);
        ReturnType Visit(Conditional node);

        ReturnType Visit(And node);
        ReturnType Visit(Or node);
        ReturnType Visit(Addition node);
        ReturnType Visit(Subtraction node);
        ReturnType Visit(Division node);
        ReturnType Visit(Multiply node);
        ReturnType Visit(GreaterThan node);
        ReturnType Visit(GreaterThanOrEqual node);
        ReturnType Visit(LessThan node);
        ReturnType Visit(LessThanOrEqual node);
        ReturnType Visit(Equal node);
        ReturnType Visit(Inequal node);        
        ReturnType Visit(Positive node);
        ReturnType Visit(Negative node);
        ReturnType Visit(Bang node);

        ReturnType Visit(Literals.Boolean node);
        ReturnType Visit(Literals.Money node);
        ReturnType Visit(Literals.Number node);
        ReturnType Visit(Literals.String node);
        ReturnType Visit(Identifier node);
    }

    // Passing in void as a generic parameter is not allowed. Apparently creating a second non-generic 
    // interface is the standard solution the .NET framework uses
    public interface IASTVisitor
    {
        void Visit(Form node);
        void Visit(Question node);
        void Visit(ComputedQuestion node);
        void Visit(Conditional node);

        void Visit(And node);
        void Visit(Or node);
        void Visit(Addition node);
        void Visit(Subtraction node);
        void Visit(Division node);
        void Visit(Multiply node);
        void Visit(GreaterThan node);
        void Visit(GreaterThanOrEqual node);
        void Visit(LessThan node);
        void Visit(LessThanOrEqual node);
        void Visit(Equal node);
        void Visit(Inequal node);
        void Visit(Positive node);
        void Visit(Negative node);
        void Visit(Bang node);

        void Visit(Literals.Boolean node);
        void Visit(Literals.Money node);
        void Visit(Literals.Number node);
        void Visit(Literals.String node);
        void Visit(Identifier node);
    }
}
