using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Renderer.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    class Slider : Widget
    {
        // TODO: Add support to slider for different types

        public override QuestionWidget CreateWidget(Types.IntegerType type)
        {
            return new SliderWidget();
        }

        public override QuestionWidget CreateWidget(Types.MoneyType type)
        {
            return new SliderWidget();
        }
    }
}
