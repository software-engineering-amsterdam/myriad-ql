namespace OffByOne.Ql.Interpreter.Controls
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values;

    using Windows = System.Windows.Controls;

    public class FormControl : Control
    {
        public FormControl(FormStatement statement, GuiEnvironment guiEnvironment, IList<Control> controls)
            : base(guiEnvironment)
        {
            this.Statement = statement;
            foreach (var control in controls)
            {
                this.Controls = this.Controls.Concat(control.Controls).ToList();
            }
        }

        public FormStatement Statement { get; }

        public override void OnCompleted()
        {
            throw new NotImplementedException();
        }

        public override void OnNext(GuiChange value)
        {
            return;
        }
    }
}
