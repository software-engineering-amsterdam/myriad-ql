namespace OffByOne.Ql.Graphics.Widgets.Base
{
    using System;
    using System.Windows.Controls;

    using OffByOne.Ql.Values.Contracts;

    public abstract class UiComponent
    {
        private WidgetStyle style;

        public bool IsHidden { get; set; }

        public bool IsEnabled { get; set; }

        public abstract IValue Value { get; set; }

        public abstract Control Control { get; }

        public virtual WidgetStyle Style
        {
            get
            {
                return this.style;
            }

            set
            {
                if (value == null)
                {
                    throw new ArgumentNullException(nameof(value));
                }

                this.ApplyStyle(value);
                this.style = value;
            }
        }

        public void ApplyStyle(WidgetStyle style)
        {
            this.Control.FontStyle = style.FontStyle;
            this.Control.FontFamily = style.FontFamily;
            this.Control.Width = style.Width;
            this.Control.Height = style.Height;
            this.Control.FontSize = style.FontSize;
        }
    }
}
