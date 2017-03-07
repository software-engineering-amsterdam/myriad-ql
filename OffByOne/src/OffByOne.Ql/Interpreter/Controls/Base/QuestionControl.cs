namespace OffByOne.Ql.Interpreter.Controls.Base
{
    using System;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Values.Contracts;

    public abstract class QuestionControl : Control, IObserver
    {
        private IValue value;

        public QuestionControl(string identifier, Expression computationExpression, GuiEnvironment guiEnvironment)
        {
            this.Identifier = identifier;
            this.Environment = guiEnvironment;
            this.ComputationExpression = computationExpression;
        }

        public Control ControlElement { get; protected set; }

        public string Identifier { get; private set; }

        public IValue Value
        {
            get
            {
                return this.value;
            }

            protected set
            {
                this.value = value;
                this.Environment.UpdateValues(this.Identifier, this.value);
            }
        }

        public Expression ComputationExpression { get; }

        public GuiEnvironment Environment { get; private set; }

        public virtual void Notify(GuiChange change)
        {
            if (this.ComputationExpression != null && change.Identifier != this.Identifier)
            {
                var evaluator = new Evaluator();
                this.Value = evaluator.Evaluate(this.ComputationExpression, change.Environment.Evaluations);
            }
        }
    }
}
