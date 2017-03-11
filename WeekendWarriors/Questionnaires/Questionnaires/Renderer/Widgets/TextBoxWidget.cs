﻿using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using Questionnaires.Renderer.Style;
using System.Windows.Media;

namespace Questionnaires.Renderer.Widgets
{
    class TextBoxWidget : QuestionWidget
    {
        private TextBox TextBox;

        public TextBoxWidget() : base(new TextBox())
        {
            TextBox = Control as TextBox;
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(StringType value)
        {
            TextBox.Text = value.GetValue();
        }

        public override void SetOnInputChanged(Renderer.InputChangedCallback inputChanged)
        {
            TextBox.TextChanged += (sender, args) => inputChanged.Invoke(this, new StringType(TextBox.Text));
        }
    }
}
