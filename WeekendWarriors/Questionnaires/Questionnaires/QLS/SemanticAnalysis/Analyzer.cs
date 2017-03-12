using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.SemanticAnalysis
{
    public class Analyzer
    {
        /*
         * The type checker detects:
        --no references to questions that are not in the QL program
        all questions of the QL program are placed by the QLS program.
        --(default) widget assignments are compatible with question types (e.g. no radio button for integer widgets).
        --you cannot place a single question multiple times.
        */

        //private HashSet<QL.AST.Question> QlQuestions = new HashSet<QL.AST.Question>();
        private Dictionary<string, QL.AST.Question> QLQuestions = new Dictionary<string, QL.AST.Question>();
        private HashSet<QL.AST.Question> PlacedQuestions = new HashSet<QL.AST.Question>();

        private Questionnaires.SemanticAnalysis.Result Result = new Questionnaires.SemanticAnalysis.Result();

        public Analyzer(List<QL.AST.Question> questions)
        {
            questions.ForEach((question) => QLQuestions[question.Identifier] = question);
        }

        public Questionnaires.SemanticAnalysis.Result Analyze(AST.StyleSheet stylesheet)
        {
            foreach(var page in stylesheet.Pages)
            {
                Visit((dynamic)page);
            }

            // Check if all questions from the QL file are placed by the QLS file
            var QuestionInQlFile = new HashSet<QL.AST.Question>(QLQuestions.Values);
            if(!QuestionInQlFile.IsSubsetOf(PlacedQuestions))
            {
                Result.AddEvent(new Questionnaires.SemanticAnalysis.Messages.Error("Not all question in the QL file have been placed by the QLS file"));
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
        }

        private void Visit(AST.QuestionWithWidget question)
        {
            // Question with this name exists as QL question
            if (!QLQuestions.ContainsKey(question.Name))
            {
                Result.AddEvent(new Questionnaires.SemanticAnalysis.Messages.Error(string.Format("Question {0} defined in QLS is not defined in the QL file", question.Name)));
                return;
            }

            var qlQuestion = QLQuestions[question.Name];

            // Check the question has not been placed before
            if (PlacedQuestions.Contains(qlQuestion))
            {
                Result.AddEvent(new Questionnaires.SemanticAnalysis.Messages.Error(string.Format("Question {0} was already placed", question.Name)));
                return;
            }

            PlacedQuestions.Add(qlQuestion);

            // Check that type of widget matches type of question
            try
            {
                question.Widget.CreateWidget((dynamic)qlQuestion.Type);
            }
            catch(NotSupportedException)
            {
                Result.AddEvent(new Questionnaires.SemanticAnalysis.Messages.Error(string.Format("Widget type {0} defined for question {1} is invalid for that question's type: {2} ", question.Widget, question.Name, qlQuestion.Type)));
            }
            
        }

        private void Visit(QLS.AST.Question question)
        {
            // Question with this name exists as QL question
            if (!QLQuestions.ContainsKey(question.Name))
            {
                Result.AddEvent(new Questionnaires.SemanticAnalysis.Messages.Error(string.Format("Question {0} defined in QLS is not defined in the QL file", question.Name)));
                return;
            }

            // Check the question has not been placed before          
            if (PlacedQuestions.Contains(QLQuestions[question.Name]))
            {
                Result.AddEvent(new Questionnaires.SemanticAnalysis.Messages.Error(string.Format("Question {0} was already placed", question.Name)));
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
            catch(NotSupportedException)
            {
                Result.AddEvent(new Questionnaires.SemanticAnalysis.Messages.Error(string.Format("Widget type {0} defined as default for question type {1} is invalid.", style.Widget, style.Type)));
            }
        }
    }
}
