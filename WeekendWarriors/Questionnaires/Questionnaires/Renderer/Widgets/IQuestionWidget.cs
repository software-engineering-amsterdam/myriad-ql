using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using static Questionnaires.Renderer.Renderer;

namespace Questionnaires.Renderer.Widgets
{
    interface IQuestionWidget
    {
        void SetLabel(string text);
        void SetQuestionValue(Questionnaires.Value.IType value);
        void SetVisibility(bool visible);

        void SetOnInputChanged(InputChangedCallback inputChanged);
    }
}
