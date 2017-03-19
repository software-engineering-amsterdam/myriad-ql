using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST;
using System.Collections.Generic;

namespace Questionnaires.QL.SemanticAnalysis
{
    class LabelUniqueNessChecker
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
            string message = "Questions";
            for (var index = 0; index < questions.Count; index++)
            {
                string seperator = ",";
                if (index == questions.Count - 2)
                    seperator = " and";
                if (index == questions.Count - 1)
                    seperator = "";

                message = string.Format("{0} {1}{2}", message, questions[index].Identifier, seperator);
            }

            message = string.Format("{0} have an idetical body: \"{1}\"", message, body);

            return message;
        }
    }

}
