using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST;
using System.Collections.Generic;
using System.Linq;

namespace Questionnaires.QL.SemanticAnalysis
{
    public class LabelUniquenessChecker
    {
        private Dictionary<string, List<Question>> QuestionBodies = new Dictionary<string, List<Question>>();

        public void AddQuestion(Question question)
        {
            if (!QuestionBodies.ContainsKey(question.Body))
            {
                QuestionBodies[question.Body] = new List<Question>();
            }

            QuestionBodies[question.Body].Add(question);
        }

        public void Check(Result result)
        {
            foreach (var questionBody in QuestionBodies)
            {
                if (questionBody.Value.Count > 1)
                {
                    result.AddEvent(new Warning(FormatMessage(questionBody.Key, questionBody.Value)));
                }
            }
        }

        private string FormatMessage(string body, List<Question> questions)
        {
            List<string> questionNames = questions.Select(q => q.Identifier).ToList();
            return string.Format("Questions {0} have an idetical body: \"{1}\"", Utility.String.FormatSequenceOfStrings(questionNames), body);
        }
    }

}
