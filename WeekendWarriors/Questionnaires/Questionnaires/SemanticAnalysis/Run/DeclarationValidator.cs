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
    class DeclarationValidator : ISemanticAnalyzerRun, IASTVisitor
    {
        protected QLContext Context;
        protected Result result = new Result();
        public IResult Analyze(INode node, QLContext context)
        {
            Context = context;
            Visit((dynamic)node);
            return result;
        }

        public void Visit(QLConditional node)
        {
            // We are only intereted in questions, so there is no need to visit the condition
            foreach (var statement in node.ThenStatements)
                Visit((dynamic)statement);
            foreach (var statement in node.ElseStatements)
                Visit((dynamic)statement);
        }

        public void Visit(QLForm node)
        {
            foreach (var statement in node.Statements)
            {
                Visit((dynamic)statement);
            }
        }

        public void Visit(QLComputedQuestion node)
        {
            Visit((dynamic)node.Question);
        }

        public void Visit(QLQuestion node)
        {
            if (!Context.ContainsQuestion(node.Identifier))
            {
                Context.AddQuestion(node.Identifier, node.Type);
                return;
            }

            /* We already have a question with this name
             * If this question is of the same type as the previous one this is a warning
             * If it is of another type, it is an error */
            var storedType = Context.GetQuestionType(node.Identifier);
            if (storedType == node.Type)
                result.AddEvent(new SemanticAnalysis.SemenaticAnalysisEvents.SemanticAnalysisWarning(string.Format("Redeclaration of question {0}", node.Identifier)));
            else
                result.AddEvent(new SemanticAnalysis.SemenaticAnalysisEvents.SemanticAnalysisError(string.Format("Redeclaration of question {0} with conflicting types {1} and {2}", node.Identifier, node.Type, storedType)));
        }

        // TODO: this is ugly. Look into creating a base class for the visitor from which we can override only the mehtods of interes.
        public void Visit(QLBoolean node)
        {
           
        }

        public void Visit(QLNumber node)
        {
            
        }

        public void Visit(QLString node)
        {
            
        }

        public void Visit(QLMoney node)
        {
            
        }

        public void Visit(QLIdentifier node)
        {

        }

        public void Visit(QLArithmeticOperation node)
        {
            
        }

        public void Visit(QLEqualityOperation node)
        {
            
        }

        public void Visit(QLUnaryOperation node)
        {
            
        }

        public void Visit(QLNegativeOperation node)
        {
            
        }

        public void Visit(QLBangOperation node)
        {
            
        }

        public void Visit(QLPositiveOperation node)
        {
            
        }

        public void Visit(QLLogicalOperation node)
        {
            
        }

        public void Visit(QLComparisonOperation node)
        {
           
        }

        public void Visit(QLBinaryOperation node)
        {
            
        }        
    }
}
