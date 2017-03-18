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
        private Window QuestionnaireWindow = new Window();
        private StackPanel QuestionnaireStack = new StackPanel();        

        public Renderer()
        {
            QuestionnaireStack.Orientation = Orientation.Vertical;
            QuestionnaireWindow.Content = QuestionnaireStack;
            QuestionnaireWindow.Title = "Questionnaire";
        }

        public void RenderModel(DocumentModel documentModel)
        {
            documentModel.Draw(QuestionnaireStack);
            QuestionnaireWindow.Show();
        }
    }
}
