using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.QLS.AST;
using Questionnaires.Renderer.Style;

namespace Questionnaires.QLS.Processing
{   
    class Processor
    {
        // A list of the questions parsed from the QL code
        Dictionary<string, QL.AST.Question> Questions = new Dictionary<string, QL.AST.Question>();
        // Stack of default styles
        Stack Styles = new Stack();

        public Processor(List<QL.AST.Question> questions)
        {
            // Fill up the dictionary
            questions.ForEach((question) => { Questions[question.Identifier] = question; });
        }

        public void Process(StyleSheet styleSheet)
        {
            foreach(var page in styleSheet.Pages)
            {
                Visit((dynamic)page);                 
            }
        }

        private void Visit(Page page)
        {
            /* Push the default style of this page into the stack
            so child sections can use them */
            AddStylesToStack(page.DefaultStyles);

            foreach (var section in page.Sections)
            {
                Visit((dynamic)section);
            }

            /* Pop the styles specific from this page from the stack */
            RemoveStylesFromStack(page.DefaultStyles);
        }

        private void Visit(Section section)
        {
            /* Push the default styles of this section onto the stack
             * so child sections/questions can use them */
            AddStylesToStack(section.Styles);

            foreach (var question in section.Questions)
            {
                Visit((dynamic)question);
            }

            RemoveStylesFromStack(section.Styles);
        }

        private void Visit(QuestionWithWidget question)
        {
            this.Questions[question.Name].Widget = question.Widget.CreateWidget();
        }

        private void Visit(Question question)
        {
            var QLQuestion = Questions[question.Name];
            var stackCopy = (Stack)Styles.Clone();

            while(stackCopy.Count > 0)
            {
                var style = (DefaultStyle)stackCopy.Pop();
                if(style.Type.GetType() == QLQuestion.Type.GetType())
                {
                    QLQuestion.Widget = style.Widget.CreateWidget();
                    WidgetStyle properties = new WidgetStyle();
                    foreach(var property in style.Properties)
                    {
                        switch(property.Key)
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
        }

        private void AddStylesToStack(List<DefaultStyle> styles)
        {
            foreach (var style in styles)
                this.Styles.Push(style);
        }

        private void RemoveStylesFromStack(List<DefaultStyle> styles)
        {
            foreach (var style in styles)
                this.Styles.Pop();
        }
    }
}
