using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Renderer.Widgets;
using Questionnaires.Types;

namespace Questionnaires.QLS.AST.Widgets
{
    class CheckBox : Widget
    {
        public override QuestionWidget CreateWidget(BooleanType type)
        {
            return new CheckBoxWidget();
        }
    }
}
