using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.AST;
using Questionnaires.AST.Operators;
using Questionnaires.AST.Visitor;

namespace Questionnaires.SemanticAnalysis.Run
{
    public class TypeChecker : ISemanticAnalyzerRun, IASTVisitor<QLType?>
    {
        private QLContext Context;
        private Result result = new Result();

        public IResult Analyze(AST.INode node, QLContext context)
        {
            this.Context = context;

            Visit((dynamic)node);
            return result;
        }

        public QLType? Visit(Form node)
        {
            foreach (var statement in node.Statements)
                Visit((dynamic)statement);            

            return null;
        }

        public QLType? Visit(AST.Question node)
        {
            List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent> events = new List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent>();
            var type = node.CheckOperandTypes(new List<QLType>(), Context, events);
            ReportEvents(events);

            return type;
        }

        public QLType? Visit(ComputedQuestion node)
        {
            return Evaluate(new List<INode> { node.Question, node.Expression }, node);
        }

        public QLType? Visit(Conditional node)
        {   
            // Make sure to visit all the then and else statements
            foreach (var statement in node.ThenStatements)
                Visit((dynamic)statement);
            foreach (var statement in node.ElseStatements)
                Visit((dynamic)statement);

            // And validate the condition
            return Evaluate(new List<INode> { node.Condition }, node);          
        }

        public QLType? Visit(Or node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(Subtraction node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(Multiply node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(GreaterThanOrEqual node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(LessThanOrEqual node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(Inequal node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(LessThan node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(Equal node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(GreaterThan node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(Division node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(Addition node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(And node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(AST.Literals.Boolean node)
        {
            return Evaluate(new List<INode> { }, node);            
        }

        public QLType? Visit(AST.Literals.Money node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        public QLType? Visit(AST.Literals.Number node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        public QLType? Visit(AST.Literals.String node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        public QLType? Visit(Identifier node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        public QLType? Visit(Binary node)
        {
            return Visit((dynamic)node);
        }

        public QLType? Visit(Positive node)
        {
            return Evaluate(new List<INode> { node.Operand }, node);
        }

        public QLType? Visit(Negative node)
        {
            return Evaluate(new List<INode> { node.Operand }, node);
        }

        public QLType? Visit(Bang node)
        {
            return Evaluate(new List<INode> { node.Operand }, node);
        }

        private QLType? Evaluate(List<INode> children, INode parent)
        {
            List<QLType> childTypes = new List<QLType>();
            bool hasInvalidChild = false;

            foreach (var child in children)
            {
                QLType? childType = Visit((dynamic)child);
                if (childType.HasValue)
                    childTypes.Add(childType.Value);
                else
                    hasInvalidChild = true;
            }

            if (!hasInvalidChild)
            {
                List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent> events = new List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent>();
                var parentType = parent.CheckOperandTypes(childTypes, Context, events);
                ReportEvents(events);
                return parentType;
            }

            return null;
        }

        private void ReportEvents(List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent> events)
        {
            // Report any warnings/errors that occurred.
            foreach (var analysisEvent in events)
                result.AddEvent(analysisEvent);
        }
    }
}
