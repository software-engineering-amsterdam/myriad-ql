using Questionnaires.QL.AST;
using Questionnaires.ErrorHandling;

namespace Questionnaires.QL.SemanticAnalysis
{
    class DeclarationValidator
    {
        private QLContext Context;
        private Result result = new Result();

        public Result Analyze(INode node, QLContext context)
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

        public void Visit(QL.AST.Question node)
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
            if (storedType.GetType() == node.Type.GetType())
                result.AddEvent(new Warning(string.Format("Redeclaration of question {0}", node.Identifier)));
            else
                result.AddEvent(new Error(string.Format("Redeclaration of question {0} with conflicting types {1} and {2}", node.Identifier, node.Type, storedType)));
        }

        public void Visit(INode node)
        {

        }
    }
}
