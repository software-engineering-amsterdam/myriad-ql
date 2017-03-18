using Questionnaires.Renderer.Widgets;

namespace Questionnaires.Types
{
    public class BooleanType : Type<bool>
    {
        public BooleanType()
        {
        }

        public BooleanType(bool value)
        {
            this.Val = value;
        }

        public override BooleanType Bang()
        {
            return new BooleanType(!this.Val);
        }

        public override BooleanType And(BooleanType value)
        {
            return new BooleanType(this.Val && value.GetValue());
        }

        public override BooleanType Or(BooleanType value)
        {
            return new BooleanType(this.Val || value.GetValue());
        }

        public override BooleanType EqualTo(BooleanType value)
        {
            return new BooleanType(this.Val == value.GetValue());
        }

        public override BooleanType InequalTo(BooleanType value)
        {
            return new BooleanType(this.Val != value.GetValue());
        }

        public override QuestionWidget GetWidget()
        {
            return new CheckBoxWidget();
        }
    }
}
