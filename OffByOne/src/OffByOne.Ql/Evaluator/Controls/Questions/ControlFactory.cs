namespace OffByOne.Ql.Evaluator.Controls.Questions
{
    using System;

    using OffByOne.LanguageCore.Ast.ValueTypes;

    using OffByOne.Ql.Ast.Statements;

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
