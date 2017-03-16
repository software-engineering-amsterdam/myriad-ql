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
using Questionnaires.RunTime;

namespace Questionnaires.Renderer
{
    public class Renderer
    {
        private Dictionary<Widgets.QuestionWidget, string> WidgetNames;
        private Window QuestionnaireWindow = new Window();
        private StackPanel QuestionnaireStack = new StackPanel();
        private Dictionary<string, QuestionWidget> Questions = new Dictionary<string, QuestionWidget>();
        private VariableStore VariableStore;

        public Renderer(VariableStore variableStore)
        {
            WidgetNames = new Dictionary<QuestionWidget, string>();
            VariableStore = variableStore;

            QuestionnaireStack.Orientation = Orientation.Vertical;
            QuestionnaireWindow.Content = QuestionnaireStack;

            QuestionnaireWindow.Show();
        }

        public void AddModel(DocumentModel documentModel)
        {
            documentModel.Draw(QuestionnaireStack);

            // TODO: I think we should be able to work without this code.
            var questions = documentModel.GetQuestions();
            foreach(var question in questions)
            {
            //    question.Widget.SetOnInputChanged(new InputChangedCallback(this.InputChanged));
                Questions.Add(question.Identifier, question.Widget);
            //    WidgetNames[question.Widget] = question.Identifier;
            }        
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

        //public delegate void InputChangedCallback(Widgets.QuestionWidget source, IType value);
        //public void InputChanged(Widgets.QuestionWidget source, IType value)
        //{
        //    Debug.Assert(WidgetNames.ContainsKey(source));
        //    //VariableStore.SetValue(WidgetNames[source], value);
        //}
    }
}
