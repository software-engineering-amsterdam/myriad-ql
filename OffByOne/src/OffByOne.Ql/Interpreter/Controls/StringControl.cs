namespace OffByOne.Ql.Interpreter.Controls
{
    using System.Collections.Generic;
    using System.Linq;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values;

    public class StringControl : QuestionControl
    {
        public StringControl(string identifier, Expression computationExpression, GuiEnvironment guiEnvironment)
            : base(identifier, computationExpression, guiEnvironment)
        {
            this.ControlElement = this.CreateElement();
            this.Value = new StringValue(string.Empty);
        }

        public override void Notify(GuiChange change)
        {
            base.Notify(change);
            ((TextBox)((ListView)this.ControlElement).Items[1]).Text = this.Value.ToString();
        }

        private Control CreateElement()
        {
            var container = new ListView();
            var label = new Label() { Content = this.Identifier };
            var input = new TextBox() { MinWidth = 200 };
            input.KeyUp += (target, eventArgs) =>
            {
                this.Value = new StringValue(input.Text);
            };
            var children = new List<Control> { label, input };
            foreach (var child in children)
            {
                container.Items.Add(child);
            }

            return container;
        }
    }
}
