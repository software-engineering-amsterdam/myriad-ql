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

namespace Questionnaires.Renderer
{
    public class Renderer
    {
        private Dictionary<Widgets.QuestionWidget, string> WidgetNames;
        private Window QuestionnaireWindow = new Window();
        private StackPanel QuestionnaireStack = new StackPanel();
        private Dictionary<string, IQuestionWidget> Questions = new Dictionary<string, IQuestionWidget>();
        private VariableStore.VariableStore VariableStore;

        public Renderer(VariableStore.VariableStore variableStore)
        {
            WidgetNames = new Dictionary<QuestionWidget, string>();
            VariableStore = variableStore;

            QuestionnaireStack.Orientation = Orientation.Vertical;
            QuestionnaireWindow.Content = QuestionnaireStack;

            QuestionnaireWindow.Show();
        }

        public void AddQuestion(QL.AST.Question question)
        {
            // Render the question by adding it to the questionnaire stack
            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);
            var questionWidget = question.Widget;
            questionWidget.SetLabel(question.Body);
            questionWidget.SetOnInputChanged(inputChangedDelegate);
            Questions.Add(question.Identifier, questionWidget);
            QuestionnaireStack.Children.Add(questionWidget);
            WidgetNames[questionWidget] = question.Identifier;
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
