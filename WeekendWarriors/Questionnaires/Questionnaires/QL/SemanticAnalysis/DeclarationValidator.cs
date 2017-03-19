using Questionnaires.QL.AST;
using Questionnaires.ErrorHandling;
using System.Collections.Generic;

namespace Questionnaires.QL.SemanticAnalysis
{
    public class DeclarationValidator
    {
        private QLContext Context;
        private Result Result = new Result();
        private LabelUniqueNessChecker LabelChecker = new LabelUniqueNessChecker();

        private Dictionary<string, List<Question>> QuestionBodies = new Dictionary<string, List<Question>>();

        public DeclarationValidator(Result result)
        {
            Result = result;
        }

        public void Analyze(INode node, QLContext context)
        {
            Context = context;
            Visit((dynamic)node);

            LabelChecker.Check(Result);
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

        public void Visit(Question node)
        {
            LabelChecker.AddQuestion(node);

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
            {
                Result.AddEvent(new Error(string.Format("Redeclaration of question {0}", node.Identifier)));
            }
            else
            {
                Result.AddEvent(new Error(string.Format("Redeclaration of question {0} with conflicting types {1} and {2}", node.Identifier, node.Type, storedType)));
            }
        }

        public void Visit(INode node)
        {

        }
    }
}
