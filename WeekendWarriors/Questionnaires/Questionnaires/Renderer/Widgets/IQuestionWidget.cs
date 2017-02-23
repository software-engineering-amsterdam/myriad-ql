using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Widgets
{
    interface IQuestionWidget
    {
        void SetLabel(string text);
        void SetQuestionValue(Questionnaires.Value.IValue value);
        void SetVisibility(Question.Visibility visibility);
    }
}
