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

        public void AddQuestion(IQuestion question)
        {
            // Render the question by adding it to the questionnaire stack
            var inputChangedDelegate = new InputChangedCallback(this.InputChanged);
            QuestionWidget questionWidget;
            
            // Ok so this was broken due to the enum being gone.
            // Alternatives: Double dispatch, try catch blocks, some other type deduction?
            /*
            switch (question.Type) 
            {
                case QuestionType.Bool:
                    questionWidget = new BooleanQuestionWidget(question.Name);                    
                    break;
                case QuestionType.Money:
                    questionWidget = new MoneyQuestionWidget(question.Name);
                    break;
                case QuestionType.Number:
                    questionWidget = new NumberQuestionWidget(question.Name);
                    break;
                case QuestionType.String:
                    questionWidget = new StringQuestionWidget(question.Name);
                    break;
                default:
                    throw new System.ComponentModel.InvalidEnumArgumentException();
            }*/
            
            //questionWidget.SetLabel(question.Body);
            //questionWidget.SetOnInputChanged(inputChangedDelegate);
            //Questions.Add(question.Name, questionWidget);
            //QuestionnaireStack.Children.Add(questionWidget);
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
