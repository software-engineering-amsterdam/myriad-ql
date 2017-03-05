using Questionnaires.Question;
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

        private BooleanQuestionWidget BuildWidget(BoolValue question, string name)
        {
            return new BooleanQuestionWidget(name);
        }

        private NumberQuestionWidget BuildWidget(IntValue question, string name)
        {
            return new NumberQuestionWidget(name);
        }

        private MoneyQuestionWidget BuildWidget(DecimalValue question, string name)
        {
            return new MoneyQuestionWidget(name);
        }

        private StringQuestionWidget BuildWidget(StringValue question, string name)
        {
            return new StringQuestionWidget(name);
        }
    }
}
