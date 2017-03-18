﻿using Questionnaires.Renderer.Widgets;

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
