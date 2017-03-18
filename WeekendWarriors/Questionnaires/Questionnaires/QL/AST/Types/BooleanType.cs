using Questionnaires.UI.Widgets;

namespace Questionnaires.Types
{
    public class BooleanType : Type<bool>
    {
        public BooleanType()
        {
        }

        public BooleanType(bool value)
        {
            Value = value;
        }

        public override BooleanType Bang()
        {
            return new BooleanType(!Value);
        }

        public override BooleanType And(BooleanType value)
        {
            return new BooleanType(Value && value.GetValue());
        }

        public override BooleanType Or(BooleanType value)
        {
            return new BooleanType(Value || value.GetValue());
        }

        public override BooleanType EqualTo(BooleanType value)
        {
            return new BooleanType(Value == value.GetValue());
        }

        public override BooleanType InequalTo(BooleanType value)
        {
            return new BooleanType(Value != value.GetValue());
        }

        public override QuestionWidget GetWidget()
        {
            return new CheckBoxWidget();
        }
    }
}
