using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.AST;
using DSL.AST.Operators;

namespace DSL.SemanticAnalysis
{
    public class Analyzer
    {
        private Dictionary<string, QLType> IdentifiertoType = new Dictionary<string, QLType>();
        // TODO: injection
        private ExpressionValidator expressionValidator = new ExpressionValidator();
        private LiteralValidator literalValidator = new LiteralValidator();
        private StatementValidator statementValidator = new StatementValidator();

        private QLContext Context;

        public Analyzer(QLContext context)
        {
            this.Context = context;

            expressionValidator.InvalidExpression += ValidatorInvalidExpression;
            literalValidator.InvalidExpression += ValidatorInvalidExpression;
            statementValidator.InvalidExpression += ValidatorInvalidExpression;
        }
        
        private void ValidatorInvalidExpression(object sender, InvalidExpressionEventArgs e)
        {
            OnSemanticError(new SemanticErrorArgs(e.Message));
        }

        public void Analyze(AST.INode node)
        {
            Visit((dynamic)node);
        } 
        
        protected QLType? Visit(QLForm node)
        {
            foreach (var statement in node.Statements)
                Visit((dynamic)statement);            

            return QLType.None;
        }

        protected QLType? Visit(QLQuestion node)
        {
            List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent> events = new List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent>();
            var type = node.CheckTypes(new List<QLType>(), Context, events);
            ReportEvents(events);

            return type;
        }

        

        protected QLType? Visit(QLComputedQuestion node)
        {
            return Evaluate(new List<INode> { node.Question, node.Expression }, node);
        }

        

        protected QLType? Visit(QLConditional node)
        {   
            // Make sure to visit all the then and else statements
            foreach (var statement in node.ThenStatements)
                Visit((dynamic)statement);
            foreach (var statement in node.ElseStatements)
                Visit((dynamic)statement);

            // And validate the condition
            return Evaluate(new List<INode> { node.Condition }, node);          
        }

        protected QLType? Visit(QLArithmeticOperation node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);                   
        }

        protected QLType? Visit(QLComparisonOperation node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        protected QLType? Visit(QLEqualityOperation node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        protected QLType? Visit(QLLogicalOperation node)
        {
            return Evaluate(new List<INode> { node.Lhs, node.Rhs }, node);
        }

        protected QLType? Visit(QLUnaryOperation node)
        {
            return Evaluate(new List<INode> { node.Operand }, node);
        }

        protected QLType? Visit(QLBoolean node)
        {
            return Evaluate(new List<INode> { }, node);            
        }
            
        protected QLType? Visit(QLMoney node)
        {
            return Evaluate(new List<INode> { }, node);
        }   

        protected QLType? Visit(QLNumber node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        protected QLType? Visit(QLString node)
        {
            return Evaluate(new List<INode> { }, node);
        }

        protected QLType? Visit(QLIdentifier node)
        {
            return Evaluate(new List<INode> { }, node);
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
                OnSemanticError(new SemanticErrorArgs(analysisEvent.ToString()));
        }

        public delegate void SemanticErrorEventHandler(object sender, SemanticErrorArgs e);
        public event SemanticErrorEventHandler SemanticError;

        protected virtual void OnSemanticError(SemanticErrorArgs e)
        {
            if (SemanticError != null)
                SemanticError(this, e);
        }

    }
}
