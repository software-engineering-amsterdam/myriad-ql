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
        public QuestionWidget BuildWidget(IType value)
        {
            return BuildWidget((dynamic)value);
        }

        private CheckBoxWidget BuildWidget(BooleanType question)
        {
            return new CheckBoxWidget();
        }

        private NumberPickerWidget BuildWidget(IntegerType question)
        {
            return new NumberPickerWidget();
        }

        private DecimalPickerWidget BuildWidget(MoneyType question)
        {
            return new DecimalPickerWidget();
        }

        private TextBoxWidget BuildWidget(StringType question)
        {
            return new TextBoxWidget();
        }
    }
}
