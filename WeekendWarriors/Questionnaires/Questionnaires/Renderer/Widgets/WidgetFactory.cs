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
        public QuestionWidget BuildWidget(IType value, string name)
        {
            return BuildWidget((dynamic)value, name);
        }

<<<<<<< HEAD
        private CheckBoxWidget BuildWidget(BoolValue question, string name)
=======
        private BooleanQuestionWidget BuildWidget(BooleanType question, string name)
>>>>>>> origin/WeekendWarriors
        {
            return new CheckBoxWidget(name);
        }

<<<<<<< HEAD
        private NumberPickerWidget BuildWidget(IntValue question, string name)
=======
        private NumberQuestionWidget BuildWidget(IntegerType question, string name)
>>>>>>> origin/WeekendWarriors
        {
            return new NumberPickerWidget(name);
        }

<<<<<<< HEAD
        private DecimalPickerWidget BuildWidget(DecimalValue question, string name)
=======
        private MoneyQuestionWidget BuildWidget(MoneyType question, string name)
>>>>>>> origin/WeekendWarriors
        {
            return new DecimalPickerWidget(name);
        }

<<<<<<< HEAD
        private TextBoxWidget BuildWidget(StringValue question, string name)
=======
        private StringQuestionWidget BuildWidget(StringType question, string name)
>>>>>>> origin/WeekendWarriors
        {
            return new TextBoxWidget(name);
        }
    }
}
