using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using Questionnaires.Types;
using Questionnaires.Renderer.Style;

namespace Questionnaires.Renderer.Widgets
{
    public abstract class QuestionWidget : StackPanel
    {
        public abstract void SetLabel(string text);
        public abstract void SetOnInputChanged(Renderer.InputChangedCallback inputChanged);
        public abstract void SetQuestionValue(IType value);
        public abstract void SetVisibility(bool visible);
        public abstract void SetStyle(WidgetStyle style);
    }
}
