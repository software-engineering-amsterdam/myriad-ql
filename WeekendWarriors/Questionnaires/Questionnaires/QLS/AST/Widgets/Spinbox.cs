using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Renderer.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    class Spinbox : Widget
    {
        public override QuestionWidget CreateWidget()
        {
            // TODO: what we return here depends on the type of the question....
            return new NumberPickerWidget();
        }
    }
}
