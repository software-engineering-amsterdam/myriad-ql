namespace OffByOne.Ql.Interpreter.Widgets
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Widgets.Base;

    public class FormWidget : Widget
    {
        public FormWidget(
            FormStatement statement,
            GuiEnvironment guiEnvironment,
            IList<Widget> controls)
            : base(guiEnvironment)
        {
            this.Statement = statement;
            foreach (var control in controls)
            {
                this.AddControls(control.Controls);
            }
        }

        public FormStatement Statement { get; }

        public override void OnObserve(AnswerInput observation)
        {
        }
    }
}
