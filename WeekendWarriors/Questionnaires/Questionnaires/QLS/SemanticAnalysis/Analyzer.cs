using Questionnaires.ErrorHandling;
using System;
using System.Collections.Generic;

namespace Questionnaires.QLS.SemanticAnalysis
{
    public class Analyzer
    {
        private Result Result;
        private PlacementChecker PlacementChecker;
        private List<RunTime.Question> QLQuestions;

        public Analyzer(Result result, IEnumerable<RunTime.Question> questions)
        {
            Result = result;
            PlacementChecker = new PlacementChecker(Result, questions);
            QLQuestions = new List<RunTime.Question>(questions);            
        }

        public Result Analyze(AST.StyleSheet stylesheet)
        {
            foreach (var page in stylesheet.Pages)
            {
                Visit((dynamic)page);
            }

            PlacementChecker.CheckIfAllQuestionsArePlaced();
            
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
            if(PlacementChecker.CheckQuestion(question.Name))
            {                
                var qlQuestion = QLQuestions.Find((q) => q.Identifier == question.Name);
                if (!CheckWidgetType(question.Widget, qlQuestion.GetValue()))
                {
                    Result.AddEvent(new Error(string.Format("Widget type {0} defined for question {1} is invalid for that question's type", question.Widget, question.Name)));
                }
            }
        }

        private void Visit(QLS.AST.Question question)
        {
            PlacementChecker.CheckQuestion(question.Name);
        }

        private void Visit(QLS.AST.DefaultStyle style)
        {
            if(!CheckWidgetType(style.Widget, style.Type))
            {
                Result.AddEvent(new Error(string.Format("Widget type {0} defined as default for question type {1} is invalid.", style.Widget, style.Type)));
            }
        }

        private bool CheckWidgetType(AST.Widgets.Widget widget, Types.IType questionType)
        {
            try
            {
                widget.CreateWidget((dynamic)questionType);
                return true;
            }
            catch (NotSupportedException)
            {
                return false;
            }
        }
    }
}
