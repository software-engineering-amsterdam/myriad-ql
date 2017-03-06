using Questionnaires.Question;
using Questionnaires.Renderer.Widgets;
using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace Questionnaires.Renderer
{
    class Renderer
    {
        private Window QuestionnaireWindow = new Window();
        private StackPanel QuestionnaireStack = new StackPanel();
        private Dictionary<string, IQuestionWidget> Questions = new Dictionary<string, IQuestionWidget>();
        private VariableStore.VariableStore VariableStore;
        private WidgetFactory WidgetFactory = new WidgetFactory();

        public Renderer(VariableStore.VariableStore variableStore)
        {
            VariableStore = variableStore;

            QuestionnaireStack.Orientation = Orientation.Vertical;
            QuestionnaireWindow.Content = QuestionnaireStack;

            QuestionnaireWindow.Show();
        }

        public void AddQuestion(QL.AST.Question question)
        {
            // Render the question by adding it to the questionnaire stack
            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);
            var questionWidget = WidgetFactory.BuildWidget(question.Type, question.Identifier);
            questionWidget.SetLabel(question.Body);
            questionWidget.SetOnInputChanged(inputChangedDelegate);
            Questions.Add(question.Identifier, questionWidget);
            QuestionnaireStack.Children.Add(questionWidget);
        }

        public void SetValue(string name, Questionnaires.Value.IValue value)
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

        public delegate void InputChangedCallback(string name, IValue value);
        public void InputChanged(string name, IValue value)
        {
            VariableStore.SetValue(name, value);
        }
    }
}
