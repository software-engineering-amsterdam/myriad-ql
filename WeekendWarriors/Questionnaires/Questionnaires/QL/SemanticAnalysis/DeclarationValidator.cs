using Questionnaires.QL.AST;
using Questionnaires.ErrorHandling;
using System.Collections.Generic;

namespace Questionnaires.QL.SemanticAnalysis
{
    public class DeclarationValidator
    {
        private QLContext Context;
        private Result Result = new Result();
        private LabelUniquenessChecker LabelChecker = new LabelUniquenessChecker();

        private Dictionary<string, List<Question>> QuestionBodies = new Dictionary<string, List<Question>>();

        public DeclarationValidator(Result result)
        {
            Result = result;
        }

        public void Analyze(INode node, QLContext context)
        {
            Context = context;
            Analyze((dynamic)node);

            LabelChecker.Check(Result);
        }

        private void Analyze(Conditional node)
        {
            // We are only intereted in questions, so there is no need to visit the condition
            foreach (var statement in node.ThenStatements)
                Analyze((dynamic)statement);
            foreach (var statement in node.ElseStatements)
                Analyze((dynamic)statement);
        }

        private void Analyze(Form node)
        {
            foreach (var statement in node.Statements)
            {
                Analyze((dynamic)statement);
            }
        }

        private void Analyze(ComputedQuestion node)
        {
            Analyze((dynamic)node.Question);
        }

        private void Analyze(Question node)
        {
            LabelChecker.AddQuestion(node);

            if (!Context.ContainsQuestion(node.Identifier))
            {
                Context.AddQuestion(node.Identifier, node.Type);
                return;
            }

            /* We already have a question with this name */
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

        private void Analyze(INode node)
        {

        }
    }
}
