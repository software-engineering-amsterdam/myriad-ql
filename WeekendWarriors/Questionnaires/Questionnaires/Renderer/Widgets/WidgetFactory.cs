using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Renderer.Widgets
{
    class WidgetFactory
    {
        public QuestionWidget BuildWidget(IValue value, string name)
        {
            return BuildWidget((dynamic)value, name);
        }

        private CheckBoxWidget BuildWidget(BoolValue question, string name)
        {
            return new CheckBoxWidget(name);
        }

        private NumberPickerWidget BuildWidget(IntValue question, string name)
        {
            return new NumberPickerWidget(name);
        }

        private DecimalPickerWidget BuildWidget(DecimalValue question, string name)
        {
            return new DecimalPickerWidget(name);
        }

        private TextBoxWidget BuildWidget(StringValue question, string name)
        {
            return new TextBoxWidget(name);
        }
    }
}
