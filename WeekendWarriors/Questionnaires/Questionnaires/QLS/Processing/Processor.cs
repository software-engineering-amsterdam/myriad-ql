using System.Collections;
using System.Collections.Generic;
using Questionnaires.QLS.AST;
using Questionnaires.UI.Widgets.Style;
using Questionnaires.RunTime.DocumentModel;

namespace Questionnaires.QLS.Processing
{
    public class Processor
    {
        private Dictionary<string, RunTime.Question> Questions = new Dictionary<string, RunTime.Question>();
        private DocumentModel DocumentModel;
        private Stack Styles = new Stack();

        public Processor(List<RunTime.Question> questions, DocumentModel documentModel)
        {
            questions.ForEach((question) => { Questions[question.Identifier] = question; });
            DocumentModel = documentModel;
            DocumentModel.Clear();
        }

        public void Process(StyleSheet styleSheet)
        {
            foreach (var page in styleSheet.Pages)
            {
                DocumentModel.AddPage(Process((dynamic)page));
            }
        }

        private RunTime.DocumentModel.Page Process(QLS.AST.Page page)
        {
            /* Push the default style of this page into the stack
            so child sections can use them */
            AddStylesToStack(page.DefaultStyles);

            var pageContainer = new RunTime.DocumentModel.Page(page.Name);
            foreach (var section in page.Sections)
            {
                pageContainer.AddSection(Process((dynamic)section));
            }

            /* Pop the styles specific from this page from the stack */
            RemoveStylesFromStack(page.DefaultStyles);

            return pageContainer;
        }

        private RunTime.DocumentModel.Section Process(AST.Section section)
        {
            /* Push the default styles of this section onto the stack
             * so child sections/questions can use them */
            AddStylesToStack(section.Styles);

            var sectionContainer = new RunTime.DocumentModel.Section(section.Name);
            foreach (var question in section.Questions)
            {
                sectionContainer.AddQuestion(Process((dynamic)question));
            }

            foreach (var sec in section.Sections)
            {
                sectionContainer.AddQuestion(Process((dynamic)sec));
            }

            RemoveStylesFromStack(section.Styles);
            return sectionContainer;
        }

        private RunTime.Question Process(QuestionWithWidget question)
        {
            Questions[question.Name].SetWidget(question.Widget);
            return Questions[question.Name];
        }

        private RunTime.Question Process(Question question)
        {
            var QLQuestion = Questions[question.Name];
            var stackCopy = (Stack)Styles.Clone();

            while (stackCopy.Count > 0)
            {
                var style = (DefaultStyle)stackCopy.Pop();
                if (style.Type.GetType() == QLQuestion.GetValue().GetType())
                {
                    QLQuestion.SetWidget(style.Widget);
                    var properties = new WidgetStyle();
                    foreach (var property in style.Properties)
                    {
                        switch (property.Key)
                        {
                            case "width": properties.Width = int.Parse(property.Value); break;
                            case "font": properties.Font = property.Value.Replace('"', ' ').Trim(); break;
                            case "fontsize": properties.FontSize = int.Parse(property.Value); break;
                            case "color": properties.Color = property.Value; break;
                        }
                    }

                    QLQuestion.SetStyle(properties);
                    break;
                }
            }
            return QLQuestion;
        }

        private void AddStylesToStack(List<DefaultStyle> styles)
        {
            foreach (var style in styles)
                Styles.Push(style);
        }

        private void RemoveStylesFromStack(List<DefaultStyle> styles)
        {
            foreach (var style in styles)
                Styles.Pop();
        }
    }
}
