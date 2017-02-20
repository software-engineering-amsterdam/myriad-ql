using Questionnaires.Question;
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
        private StackPanel QuestionnaireStack;
        // Don't really want to store this I think
        private Window QuestionnaireWindow;
        // this thing here is horrible
        private Dictionary<string, Tuple<IQuestion, StackPanel>> Questions = new Dictionary<string, Tuple<IQuestion, StackPanel>>();

        public Renderer()
        {
            QuestionnaireStack = new StackPanel();
            QuestionnaireStack.Orientation = Orientation.Vertical;

            QuestionnaireWindow = new Window();
            QuestionnaireWindow.Content = QuestionnaireStack;

            QuestionnaireWindow.Show();
        }

        public void AddQuestion(IQuestion question)
        {
            // Render the question by adding it to the questionnaire stack
            var questionStack = new StackPanel();
            questionStack.Orientation = Orientation.Horizontal;

            var labelBox = new TextBlock();
            labelBox.Text = question.Body;
            questionStack.Children.Add(labelBox);
            
            // Add the right value picker
            switch (question.Type)
            {
                case QuestionType.Bool:
                    questionStack.Children.Add(ObtainInputWidgetForBool(question));
                    break;
                case QuestionType.Money:
                    questionStack.Children.Add(ObtainInputWidgetForMoney(question));
                    break;
                case QuestionType.Number:
                    questionStack.Children.Add(ObtainInputWidgetForNumber(question));
                    break;
                case QuestionType.String:
                    questionStack.Children.Add(ObtainInputWidgetForString(question));
                    break;
                default:
                    throw new System.ComponentModel.InvalidEnumArgumentException();
            }
            
            Questions.Add(question.Name, new Tuple<IQuestion, StackPanel>(question, questionStack));
            QuestionnaireStack.Children.Add(questionStack);
            SetVisibility(question.Name, question.Visibility);
        }

        public void SetValue(string name, Questionnaires.Value.IValue value)
        {
            //Questions[name].Item1.Value = value; // This seems like a design flaw xD

            // Rebuild the questions
            QuestionnaireStack = new StackPanel();
            QuestionnaireStack.Orientation = Orientation.Vertical;

            foreach (var question in Questions)
            {
                QuestionnaireStack.Children.Add(Questions[question.Key].Item2);
            }

            QuestionnaireWindow.Content = QuestionnaireStack;
        }

        public void SetVisibility(string name, Question.Visibility visibility)
        {
            var questionAndPanel = Questions[name];

            if (questionAndPanel.Item1.Visibility == Question.Visibility.Visible)
            {
                questionAndPanel.Item2.Visibility = System.Windows.Visibility.Visible;
            }
            else
            {
                questionAndPanel.Item2.Visibility = System.Windows.Visibility.Hidden;
            }
        }

        private CheckBox ObtainInputWidgetForBool(IQuestion question)
        {
            var checkBox = new CheckBox();
            checkBox.IsChecked = question.Value.AsBool();
            return checkBox;
        }

        private TextBox ObtainInputWidgetForMoney(IQuestion question)
        {
            var inputField = new TextBox();
            inputField.Text = question.Value.AsDecimal().ToString();
            return inputField;
        }

        private TextBox ObtainInputWidgetForNumber(IQuestion question)
        {
            var inputField = new TextBox();
            inputField.Text = question.Value.AsInt().ToString();
            return inputField;
        }

        private TextBox ObtainInputWidgetForString(IQuestion question)
        {
            var inputField = new TextBox();
            inputField.Text = question.Value.ToString(); //\todo: This is the wrong call I think but can't escape it since value doesn't define AsString()
            return inputField;
        }
    }
}
