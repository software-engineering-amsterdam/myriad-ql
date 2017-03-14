namespace OffByOne.Ql.Interpreter.Widgets.Base
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Common;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Values.Contracts;

    public abstract class QuestionWidget : Widget, IObserver<AnswerInput>
    {
        private IValue value;

        public QuestionWidget(IValue value, QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(guiEnvironment)
        {
            this.Statement = statement;
            this.Value = value;
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

        public override void OnObserve(AnswerInput value)
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
