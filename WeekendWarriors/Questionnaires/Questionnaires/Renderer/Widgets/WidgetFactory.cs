using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Renderer.Widgets
{
    class WidgetFactory
    {
        public QuestionWidget BuildWidget(IType value, string name)
        {
            return BuildWidget((dynamic)value, name);
        }

        private CheckBoxWidget BuildWidget(BooleanType question, string name)
        {
            return new CheckBoxWidget(name);
        }

        private NumberPickerWidget BuildWidget(IntegerType question, string name)
        {
            return new NumberPickerWidget(name);
        }

        private DecimalPickerWidget BuildWidget(MoneyType question, string name)
        {
            return new DecimalPickerWidget(name);
        }

        private TextBoxWidget BuildWidget(StringType question, string name)
        {
            return new TextBoxWidget(name);
        }
    }
}
