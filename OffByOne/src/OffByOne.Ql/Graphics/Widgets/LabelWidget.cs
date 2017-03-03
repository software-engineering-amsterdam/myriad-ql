namespace OffByOne.Ql.Graphics.Widgets
{
    using System;
    using System.Windows.Controls;

    using OffByOne.Ql.Graphics.Widgets.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Values.Contracts;

    public class LabelWidget : UiComponent
    {
        private readonly Label control;

        public LabelWidget(string text)
        {
            this.control = new Label { Content = text };
        }

        public override IValue Value
        {
            get
            {
                return new StringValue(this.control.Content.ToString());
            }

            set
            {
                if (value == null)
                {
                    throw new ArgumentNullException(nameof(value));
                }

                this.control.Content = value.ToString();
            }
        }

        public override Control Control => this.control;
    }
}
