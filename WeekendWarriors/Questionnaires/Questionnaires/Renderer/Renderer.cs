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

        public Renderer(VariableStore.VariableStore variableStore)
        {
            VariableStore = variableStore;

            QuestionnaireStack.Orientation = Orientation.Vertical;
            QuestionnaireWindow.Content = QuestionnaireStack;

            QuestionnaireWindow.Show();
        }

        public void AddQuestion(BoolValue type, IQuestion question)
        {
            // Render the question by adding it to the questionnaire stack
            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);
            var questionWidget = new BooleanQuestionWidget(question.Name);
            questionWidget.SetLabel(question.Body);
            questionWidget.SetOnInputChanged(inputChangedDelegate);
            Questions.Add(question.Name, questionWidget);
            QuestionnaireStack.Children.Add(questionWidget);
        }

        public void AddQuestion(DecimalValue type, IQuestion question)
        {
            // Render the question by adding it to the questionnaire stack
            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);
            var questionWidget = new MoneyQuestionWidget(question.Name);
            questionWidget.SetLabel(question.Body);
            questionWidget.SetOnInputChanged(inputChangedDelegate);
            Questions.Add(question.Name, questionWidget);
            QuestionnaireStack.Children.Add(questionWidget);
        }

        public void AddQuestion(IntValue type, IQuestion question)
        {
            // Render the question by adding it to the questionnaire stack
            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);
            var questionWidget = new NumberQuestionWidget(question.Name);
            questionWidget.SetLabel(question.Body);
            questionWidget.SetOnInputChanged(inputChangedDelegate);
            Questions.Add(question.Name, questionWidget);
            QuestionnaireStack.Children.Add(questionWidget);
        }

        public void AddQuestion(StringValue type, IQuestion question)
        {
            // Render the question by adding it to the questionnaire stack
            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);
            var questionWidget = new StringQuestionWidget(question.Name);
            questionWidget.SetLabel(question.Body);
            questionWidget.SetOnInputChanged(inputChangedDelegate);
            Questions.Add(question.Name, questionWidget);
            QuestionnaireStack.Children.Add(questionWidget);
        }

        public void SetValue(string name, Questionnaires.Value.IValue value)
        {
            Questions[name].SetQuestionValue(value);
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
