namespace OffByOne.Ql.Interpreter.Controls.Base
{
    using System;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Values.Contracts;

    using Windows = System.Windows.Controls;

    public abstract class QuestionControl : Control
    {
        private IValue value;
        private IDisposable unsubscriber;

        public QuestionControl(QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(guiEnvironment)
        {
            this.Statement = statement;
        }

        public QuestionStatement Statement { get; }

        public IValue Value
        {
            get
            {
                return this.value;
            }

            protected set
            {
                this.value = value;
                this.Environment.UpdateValues(this.Statement.Identifier, this.value);
            }
        }

        public override void OnNext(GuiChange value)
        {
            var hasExpression = this.Statement.ComputationExpression != null;
            var isUpdateByOther = value.Identifier != this.Statement.Identifier;
            if (hasExpression && isUpdateByOther)
            {
                var evaluator = new Evaluator();
                this.Value = evaluator.Evaluate(
                    this.Statement.ComputationExpression,
                    value.Environment.Evaluations);
            }
        }
    }
}
