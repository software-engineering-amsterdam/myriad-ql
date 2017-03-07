using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Renderer.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    class Text : Widget
    {
        public override QuestionWidget CreateWidget()
        {
            return new TextBoxWidget();
        }
    }
}
