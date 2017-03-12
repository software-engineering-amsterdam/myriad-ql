using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Renderer.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    class DropDown : Widget
    {
        public override QuestionWidget CreateWidget(Types.BooleanType type)
        {
            return new ComboBoxWidget();
        }
    }
}
