using Questionnaires.Renderer.Widgets;
using System;

namespace Questionnaires.Types
{
    public class StringType : Type<String>
    {
        public StringType()
        {
        }

        public StringType(String value)
        {
            Val = value;
        }

        public override BooleanType EqualTo(StringType value)
        {
            return new BooleanType(Val == value.GetValue());
        }

        public override BooleanType InequalTo(StringType value)
        {
            return new BooleanType(Val != value.GetValue());
        }

        public override QuestionWidget GetWidget()
        {
            return new TextBoxWidget();
        }
    }
}
