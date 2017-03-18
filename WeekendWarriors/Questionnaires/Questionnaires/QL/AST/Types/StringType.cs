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
            Value = value;
        }

        public override BooleanType EqualTo(StringType value)
        {
            return new BooleanType(Value == value.GetValue());
        }

        public override BooleanType InequalTo(StringType value)
        {
            return new BooleanType(Value != value.GetValue());
        }

        public override QuestionWidget GetWidget()
        {
            return new TextBoxWidget();
        }
    }
}
