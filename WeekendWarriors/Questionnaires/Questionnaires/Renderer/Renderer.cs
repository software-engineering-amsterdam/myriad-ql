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
        private StackPanel QuestionnaireStack = new StackPanel();
        private Dictionary<string, IQuestionWidget> Questions = new Dictionary<string, IQuestionWidget>();

        public Renderer()
        {
            var questionnaireWindow = new Window();

            QuestionnaireStack.Orientation = Orientation.Vertical;
            questionnaireWindow.Content = QuestionnaireStack;

            questionnaireWindow.Show();
        }

        public void AddQuestion(IQuestion question)
        {
            // Render the question by adding it to the questionnaire stack
            IQuestionWidget questionWidget;

            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);

            switch (question.Type)
            {
                case QuestionType.Bool:
                    questionWidget = new BooleanQuestionWidget(question.Name);
                    questionWidget.SetLabel(question.Body);
                    questionWidget.SetQuestionValue(question.Value);
                    questionWidget.SetOnInputChanged(inputChangedDelegate);

                    Questions.Add(question.Name, questionWidget);
                    QuestionnaireStack.Children.Add((BooleanQuestionWidget)questionWidget);
                    break;
                case QuestionType.Money:
                    questionWidget = new MoneyQuestionWidget(question.Name);
                    questionWidget.SetLabel(question.Body);
                    questionWidget.SetQuestionValue(question.Value);
                    questionWidget.SetOnInputChanged(inputChangedDelegate);

                    Questions.Add(question.Name, questionWidget);
                    QuestionnaireStack.Children.Add((MoneyQuestionWidget)questionWidget);
                    break;
                case QuestionType.Number:
                    questionWidget = new NumberQuestionWidget(question.Name);
                    questionWidget.SetLabel(question.Body);
                    questionWidget.SetQuestionValue(question.Value);
                    questionWidget.SetOnInputChanged(inputChangedDelegate);

                    Questions.Add(question.Name, questionWidget);
                    QuestionnaireStack.Children.Add((NumberQuestionWidget)questionWidget);
                    break;
                case QuestionType.String:
                    questionWidget = new StringQuestionWidget(question.Name);
                    questionWidget.SetLabel(question.Body);
                    questionWidget.SetQuestionValue(question.Value);
                    questionWidget.SetOnInputChanged(inputChangedDelegate);

                    Questions.Add(question.Name, questionWidget);
                    QuestionnaireStack.Children.Add((StringQuestionWidget)questionWidget);
                    break;
                default:
                    throw new System.ComponentModel.InvalidEnumArgumentException();
            }
        }

        public void SetValue(string name, Questionnaires.Value.IValue value)
        {
            Questions[name].SetQuestionValue(value);
        }

        public void SetVisibility(string name, Question.Visibility visibility)
        {
            Questions[name].SetVisibility(visibility);
        }

        public delegate void InputChangedCallback(string name, IValue value);
        public void InputChanged(string name, IValue value)
        {
            var x = 10;
        }
         
    }
}
