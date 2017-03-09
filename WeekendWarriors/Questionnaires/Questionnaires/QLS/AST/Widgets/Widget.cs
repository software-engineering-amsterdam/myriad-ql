using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.AST.Widgets
{
    public abstract class Widget : INode
    {
        public abstract Questionnaires.Renderer.Widgets.QuestionWidget CreateWidget();
    }
}
