using System.Collections;
using System.Collections.Generic;
using Questionnaires.QLS.AST;
using Questionnaires.Renderer.Style;
using Questionnaires.Renderer.Containers;

namespace Questionnaires.QLS.Processing
{
    class Processor
    {
        // A list of the questions parsed from the QL code
        Dictionary<string, RunTime.Question> Questions = new Dictionary<string, RunTime.Question>();
        // Document model
        DocumentModel DocumentModel;
        // Stack of default styles
        Stack Styles = new Stack();

        public Processor(List<RunTime.Question> questions, DocumentModel documentModel)
        {
            // Fill up the dictionary
            questions.ForEach((question) => { Questions[question.Identifier] = question; });
            DocumentModel = documentModel;
            DocumentModel.Clear();
        }

        public void Process(StyleSheet styleSheet)
        {
            foreach (var page in styleSheet.Pages)
            {
                DocumentModel.AddPage(Visit((dynamic)page));
            }
        }

        private Renderer.Containers.Page Visit(QLS.AST.Page page)
        {
            /* Push the default style of this page into the stack
            so child sections can use them */
            AddStylesToStack(page.DefaultStyles);

            var pageContainer = new Renderer.Containers.Page(page.Name);
            foreach (var section in page.Sections)
            {
                pageContainer.AddSection(Visit((dynamic)section));
            }

            /* Pop the styles specific from this page from the stack */
            RemoveStylesFromStack(page.DefaultStyles);

            return pageContainer;
        }

        private Renderer.Containers.Section Visit(QLS.AST.Section section)
        {
            /* Push the default styles of this section onto the stack
             * so child sections/questions can use them */
            AddStylesToStack(section.Styles);

            var sectionContainer = new Renderer.Containers.Section(section.Name);
            foreach (var question in section.Questions)
            {
                sectionContainer.AddQuestion(Visit((dynamic)question));
            }

            foreach (var sec in section.Sections)
            {
                sectionContainer.AddQuestion(Visit((dynamic)sec));
            }

            RemoveStylesFromStack(section.Styles);
            return sectionContainer;
        }

        private RunTime.Question Visit(QuestionWithWidget question)
        {
            Questions[question.Name].SetWidget(question.Widget);
            return Questions[question.Name];
        }

        private RunTime.Question Visit(Question question)
        {
            var QLQuestion = Questions[question.Name];
            var stackCopy = (Stack)Styles.Clone();

            while (stackCopy.Count > 0)
            {
                var style = (DefaultStyle)stackCopy.Pop();
                if (style.Type.GetType() == QLQuestion.Type.GetType()) // TODO: The one place where we use typeof. Can we do better?
                {
                    QLQuestion.SetWidget(style.Widget);
                    WidgetStyle properties = new WidgetStyle();
                    foreach (var property in style.Properties)
                    {
                        switch (property.Key)
                        {
                            case "width": properties.Width = int.Parse(property.Value); break;
                            case "font": properties.Font = property.Value.Replace('"', ' ').Trim(); break; // TODO: OMG this is so horrible it makes my eyes bleed :'(
                            case "fontsize": properties.FontSize = int.Parse(property.Value); break;
                            case "color": properties.Color = property.Value; break;
                        }
                    }

                    QLQuestion.Widget.SetStyle(properties);

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
