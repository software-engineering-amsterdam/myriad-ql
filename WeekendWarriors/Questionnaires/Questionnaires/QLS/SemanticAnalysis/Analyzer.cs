using Questionnaires.ErrorHandling;
using System;
using System.Collections.Generic;

namespace Questionnaires.QLS.SemanticAnalysis
{
    public class Analyzer
    {
        private Dictionary<string, RunTime.Question> QLQuestions = new Dictionary<string, RunTime.Question>();
        private HashSet<RunTime.Question> PlacedQuestions = new HashSet<RunTime.Question>();
        private Result Result;

        public Analyzer(Result result, List<RunTime.Question> questions)
        {
            Result = result;
            questions.ForEach((question) => QLQuestions[question.Identifier] = question);
        }

        public Result Analyze(AST.StyleSheet stylesheet)
        {
            foreach (var page in stylesheet.Pages)
            {
                Visit((dynamic)page);
            }

            // Check if all questions from the QL file are placed by the QLS file
            var QuestionInQlFile = new HashSet<RunTime.Question>(QLQuestions.Values);
            if (!QuestionInQlFile.IsSubsetOf(PlacedQuestions))
            {
                Result.AddEvent(new Error("Not all question in the QL file have been placed by the QLS file"));
            }
            return Result;
        }

        private void Visit(QLS.AST.Page page)
        {
            foreach (var section in page.Sections)
            {
                Visit((dynamic)section);
            }

            foreach (var style in page.DefaultStyles)
            {
                Visit((dynamic)style);
            }
        }

        private void Visit(QLS.AST.Section section)
        {
            foreach (var question in section.Questions)
            {
                Visit((dynamic)question);
            }

            foreach (var style in section.Styles)
            {
                Visit((dynamic)style);
            }

            foreach (var innerSection in section.Sections)
            {
                Visit((dynamic)innerSection);
            }
        }

        private void Visit(AST.QuestionWithWidget question)
        {
            // Question with this name exists as QL question
            if (!QLQuestions.ContainsKey(question.Name))
            {
                Result.AddEvent(new Error(string.Format("Question {0} defined in QLS is not defined in the QL file", question.Name)));
                return;
            }

            var qlQuestion = QLQuestions[question.Name];

            // Check the question has not been placed before
            if (PlacedQuestions.Contains(qlQuestion))
            {
                Result.AddEvent(new Error(string.Format("Question {0} was already placed", question.Name)));
                return;
            }

            PlacedQuestions.Add(qlQuestion);

            // Check that type of widget matches type of question
            try
            {
                qlQuestion.SetWidget(question.Widget);
            }
            catch (NotSupportedException)
            {
                Result.AddEvent(new Error(string.Format("Widget type {0} defined for question {1} is invalid for that question's type", question.Widget, question.Name)));
            }

        }

        private void Visit(QLS.AST.Question question)
        {
            // Question with this name exists as QL question
            if (!QLQuestions.ContainsKey(question.Name))
            {
                Result.AddEvent(new Error(string.Format("Question {0} defined in QLS is not defined in the QL file", question.Name)));
                return;
            }

            // Check the question has not been placed before          
            if (PlacedQuestions.Contains(QLQuestions[question.Name]))
            {
                Result.AddEvent(new Error(string.Format("Question {0} was already placed", question.Name)));
                return;
            }

            PlacedQuestions.Add(QLQuestions[question.Name]);
        }

        private void Visit(QLS.AST.DefaultStyle style)
        {
            // Check if the style type matches the widget type
            try
            {
                style.Widget.CreateWidget((dynamic)style.Type);
            }
            catch (NotSupportedException)
            {
                Result.AddEvent(new Error(string.Format("Widget type {0} defined as default for question type {1} is invalid.", style.Widget, style.Type)));
            }
        }
    }
}
