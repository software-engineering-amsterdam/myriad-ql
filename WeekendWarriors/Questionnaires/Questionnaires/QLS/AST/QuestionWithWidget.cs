using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.QLS.AST.Widgets;

namespace Questionnaires.QLS.AST
{
    class QuestionWithWidget : Question
    {
        public Widget Widget{ get; }
        public QuestionWithWidget(string name, Widget widget) : base(name)
        {
            this.Widget = widget;
        }
    }
}
