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
            this.Dependencies.UnionWith(statement.GetDependencies());
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
            if (!this.Statement.IsComputable(value.Identifier))
            {
                return;
            }

            var evaluator = new Evaluator();
            this.Value = evaluator.Evaluate(
                this.Statement.ComputationExpression,
                value.Environment.Evaluations);
        }
    }
}
