﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.QLS.AST;
using Questionnaires.Renderer.Style;
using Questionnaires.Renderer.Containers;

namespace Questionnaires.QLS.Processing
{   
    class Processor
    {
        // A list of the questions parsed from the QL code
        Dictionary<string, QL.AST.Question> Questions = new Dictionary<string, QL.AST.Question>();
        // Document model
        DocumentModel DocumentModel = new DocumentModel();
        // Stack of default styles
        Stack Styles = new Stack();

        public Processor(List<QL.AST.Question> questions, DocumentModel documentModel)
        {
            // Fill up the dictionary
            questions.ForEach((question) => { Questions[question.Identifier] = question; });
            this.DocumentModel = documentModel;
        }

        public void Process(StyleSheet styleSheet)
        {

            DocumentModel.Pages = new List<Renderer.Containers.Page>();
            foreach(var page in styleSheet.Pages)
            {
                DocumentModel.Pages.Add(Visit((dynamic)page));  
            }
        }

        private Renderer.Containers.Page Visit(QLS.AST.Page page)
        {
            /* Push the default style of this page into the stack
            so child sections can use them */
            AddStylesToStack(page.DefaultStyles);

            var pageContainer = new Renderer.Containers.Page();
            pageContainer.Sections = new List<Renderer.Containers.Section>();
            foreach (var section in page.Sections)
            {
               pageContainer.Sections.Add(Visit((dynamic)section));
            }
            pageContainer.Name = page.Name;

            /* Pop the styles specific from this page from the stack */
            RemoveStylesFromStack(page.DefaultStyles);

            return pageContainer;
        }

        private Renderer.Containers.Section Visit(QLS.AST.Section section)
        {
            /* Push the default styles of this section onto the stack
             * so child sections/questions can use them */
            AddStylesToStack(section.Styles);

            var sectionContainer = new Renderer.Containers.Section();
            sectionContainer.Questions = new List<QL.AST.Question>();
            foreach (var question in section.Questions)
            {
                sectionContainer.Questions.Add(Visit((dynamic)question));
            }

            foreach (var sec in section.Sections)
            {
                sectionContainer.Sections.Add(Visit((dynamic)sec));
            }
            sectionContainer.Name = section.Name;

            RemoveStylesFromStack(section.Styles);
            return sectionContainer;
        }

        private QL.AST.Question Visit(QuestionWithWidget question)
        {
            this.Questions[question.Name].Widget = question.Widget.CreateWidget((dynamic)this.Questions[question.Name].Type);
            return this.Questions[question.Name];
        }

        private QL.AST.Question Visit(Question question)
        {
            var QLQuestion = Questions[question.Name];
            var stackCopy = (Stack)Styles.Clone();

            while(stackCopy.Count > 0)
            {
                var style = (DefaultStyle)stackCopy.Pop();
                if(style.Type.GetType() == QLQuestion.Type.GetType())
                {
                    QLQuestion.Widget = style.Widget.CreateWidget((dynamic)this.Questions[question.Name].Type);
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
            return QLQuestion;
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
