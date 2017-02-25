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
        protected QLContext Context;
        protected Result result = new Result();

        public IResult Analyze(AST.INode node, QLContext context)
        {
            this.Context = context;

            Visit((dynamic)node);
            return result;
        }

        public QLType? Visit(QLForm node)
        {
            foreach (var statement in node.Statements)
                Visit((dynamic)statement);            

            return QLType.None;
        }

        public QLType? Visit(QLQuestion node)
        {
            List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent> events = new List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent>();
            var type = node.CheckTypes(new List<QLType>(), Context, events);
            ReportEvents(events);

            return type;
        }

        public QLType? Visit(QLComputedQuestion node)
        {
            return Evaluate(new List<INode> { node.Question, node.Expression }, node);
        }

        public QLType? Visit(QLConditional node)
        {   
            // Make sure to visit all the then and else statements
            foreach (var statement in node.ThenStatements)
                Visit((dynamic)statement);
            foreach (var statement in node.ElseStatements)
                Visit((dynamic)statement);

            // And validate the condition
            return Evaluate(new List<INode> { node.Condition }, node);          
        }

        public QLType? Visit(QLArithmeticOperation node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);                   
        }

        public QLType? Visit(QLComparisonOperation node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(QLEqualityOperation node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(QLLogicalOperation node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        public QLType? Visit(QLUnaryOperation node)
        {
            return Evaluate(new List<INode> { node.Operand }, node);
        }

        public QLType? Visit(QLBoolean node)
        {
            return Evaluate(new List<INode> { }, node);            
        }

        public QLType? Visit(QLMoney node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        public QLType? Visit(QLNumber node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        public QLType? Visit(QLString node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        public QLType? Visit(QLIdentifier node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        public QLType? Visit(QLBinaryOperation node)
        {
            return Visit((dynamic)node);
        }

        public QLType? Visit(QLPositiveOperation node)
        {
            return Visit((dynamic)node);
        }

        public QLType? Visit(QLNegativeOperation node)
        {
            return Visit((dynamic)node);
        }

        public QLType? Visit(QLBangOperation node)
        {
            return Visit((dynamic)node);
        }

        protected QLType? Evaluate(List<INode> children, INode parent)
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
                var parentType = parent.CheckTypes(childTypes, Context, events);
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
