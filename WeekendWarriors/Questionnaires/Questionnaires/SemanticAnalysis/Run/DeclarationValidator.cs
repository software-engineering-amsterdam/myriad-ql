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
        private QLContext Context;
        private Result result = new Result();

        public IResult Analyze(INode node, QLContext context)
        {
            Context = context;
            Visit((dynamic)node);
            return result;
        }

        public void Visit(Conditional node)
        {
            // We are only intereted in questions, so there is no need to visit the condition
            foreach (var statement in node.ThenStatements)
                Visit((dynamic)statement);
            foreach (var statement in node.ElseStatements)
                Visit((dynamic)statement);
        }

        public void Visit(Form node)
        {
            foreach (var statement in node.Statements)
            {
                Visit((dynamic)statement);
            }
        }

        public void Visit(ComputedQuestion node)
        {
            Visit((dynamic)node.Question);
        }

        public void Visit(AST.Question node)
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
        
        public void Visit(Or node)
        {
            
        }

        public void Visit(Subtraction node)
        {
           
        }

        public void Visit(Multiply node)
        {
            
        }

        public void Visit(GreaterThanOrEqual node)
        {
            
        }

        public void Visit(LessThanOrEqual node)
        {
            
        }

        public void Visit(Inequal node)
        {
           
        }

        public void Visit(Equal node)
        {
            
        }

        public void Visit(LessThan node)
        {
            
        }

        public void Visit(GreaterThan node)
        {
           
        }

        public void Visit(Division node)
        {
           
        }

        public void Visit(Addition node)
        {
            
        }

        public void Visit(And node)
        {
            
        }
        
        public void Visit(AST.Literals.Boolean node)
        {
           
        }

        public void Visit(AST.Literals.Number node)
        {
            
        }

        public void Visit(AST.Literals.String node)
        {
            
        }

        public void Visit(AST.Literals.Money node)
        {
            
        }

        public void Visit(Identifier node)
        {

        }

        public void Visit(Negative node)
        {
            
        }

        public void Visit(Bang node)
        {
            
        }

        public void Visit(Positive node)
        {
            
        }
      
    }
}
