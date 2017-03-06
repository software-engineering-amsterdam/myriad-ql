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

        private BooleanQuestionWidget BuildWidget(BooleanType question, string name)
        {
            return new BooleanQuestionWidget(name);
        }

        private NumberQuestionWidget BuildWidget(IntegerType question, string name)
        {
            return new NumberQuestionWidget(name);
        }

        private MoneyQuestionWidget BuildWidget(MoneyType question, string name)
        {
            return new MoneyQuestionWidget(name);
        }

        private StringQuestionWidget BuildWidget(StringType question, string name)
        {
            return new StringQuestionWidget(name);
        }
    }
}
