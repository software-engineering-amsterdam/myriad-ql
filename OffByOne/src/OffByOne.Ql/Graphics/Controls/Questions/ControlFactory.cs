namespace OffByOne.Ql.Graphics.Controls.Questions
{
    using System;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.ValueTypes;

    public class ControlFactory
    {
        public QuestionControl CreateControl(QuestionStatement statement)
        {
            switch (statement.Type)
            {
                case BooleanValueType _:
                    return new BooleanControl(statement);
                case DateValueType _:
                    return new DateControl(statement);
                case DecimalValueType _:
                    return new FloatControl(statement);
                case IntegerValueType _:
                    return new IntegerControl(statement);
                case MoneyValueType _:
                    return new MoneyControl(statement);
                case StringValueType _:
                    return new StringControl(statement);
                default:
                    throw new ArgumentOutOfRangeException(nameof(statement.Type), "Unsupported question type.");
            }
        }
    }
}
