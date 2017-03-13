using Questionnaires.Renderer.Containers;
using Questionnaires.Renderer.Style;
using Questionnaires.Renderer.Widgets;
using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace Questionnaires.Renderer
{
    public class Renderer
    {
        private Dictionary<Widgets.QuestionWidget, string> WidgetNames;
        private Window QuestionnaireWindow = new Window();
        private StackPanel QuestionnaireStack = new StackPanel();
        private Dictionary<string, QuestionWidget> Questions = new Dictionary<string, QuestionWidget>();
        private VariableStore.VariableStore VariableStore;

        public Renderer(VariableStore.VariableStore variableStore)
        {
            WidgetNames = new Dictionary<QuestionWidget, string>();
            VariableStore = variableStore;

            QuestionnaireStack.Orientation = Orientation.Vertical;
            QuestionnaireWindow.Content = QuestionnaireStack;

            QuestionnaireWindow.Show();
        }

        public void AddModel(DocumentModel documentModel)
        {
            foreach (var page in documentModel.Pages)
            {
                var pageWidget = new StackPanel();
                pageWidget.Orientation = Orientation.Vertical;
                pageWidget.Name = page.Name;

                // Without a QLS we can have questions in the page
                foreach (var question in page.Questions)
                {
                    pageWidget.Children.Add(AddQuestion(question));
                }
                
                foreach (var section in page.Sections)
                {
                    pageWidget.Children.Add(AddSection(section));
                }
                
                pageWidget.Width = 1000;
                pageWidget.Background = new SolidColorBrush(Colors.Green);
                QuestionnaireStack.Children.Add(pageWidget);
            }
        }

        private StackPanel AddSection(Section section)
        {
            var panel = new StackPanel();
            panel.Orientation = Orientation.Vertical;
            
            foreach (var sec in section.Sections)
            {
                panel.Children.Add(AddSection(sec));
            }
            
            foreach (var question in section.Questions)
            {
                panel.Children.Add(AddQuestion(question));
            }
            
            panel.Width = 500;
            panel.Background = new SolidColorBrush(Colors.Red);
            return panel;
        }

        private QuestionWidget AddQuestion(QL.AST.Question question)
        {
            // Render the question by adding it to the questionnaire stack
            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);
            var questionWidget = question.Widget;
            questionWidget.SetLabel(question.Body);
            questionWidget.SetOnInputChanged(inputChangedDelegate);

            Questions.Add(question.Identifier, questionWidget);
            WidgetNames[questionWidget] = question.Identifier;
            return questionWidget;
        }

        public void SetValue(string name, Questionnaires.Types.IType value)
        {
            Questions[name].SetQuestionValue((dynamic)value);
        }

        public void SetVisibility(string name, bool visible)
        {
            Questions[name].SetVisibility(visible);
        }

        public void SetWindowTitle(string title)
        {
            QuestionnaireWindow.Title = title;
        }

        public delegate void InputChangedCallback(Widgets.QuestionWidget source, IType value);
        public void InputChanged(Widgets.QuestionWidget source, IType value)
        {
            Debug.Assert(WidgetNames.ContainsKey(source));
            VariableStore.SetValue(WidgetNames[source], value);
        }
    }
}
