namespace OffByOne.Ql.Interpreter.Widgets
{
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values;

    public class VisibilityWidget : Widget
    {
        public VisibilityWidget(
            IfStatement statement,
            GuiEnvironment guiEnvironment,
            IList<Widget> ifControls,
            IList<Widget> elseControls)
            : base(guiEnvironment)
        {
            this.Statement = statement;
            this.IfControls = ifControls;
            this.ElseControls = elseControls;
            this.AddToControls(ifControls);
            this.AddToControls(elseControls);

            this.Dependencies.UnionWith(statement.GetDependencies());
            this.EvaluateVisibility();
        }

        public IList<Widget> IfControls { get; }

        public IList<Widget> ElseControls { get; }

        public IfStatement Statement { get; }

        public override void OnObserve(AnswerInput value)
        {
            if (!this.Statement.IsComputable(value.Identifier))
            {
                return;
            }

            this.EvaluateVisibility();
        }

        private void EvaluateVisibility()
        {
            var evaluator = new Evaluator();
            var condition = (BooleanValue)evaluator.Evaluate(
                this.Statement.Condition,
                this.Environment.Evaluations);
            this.UpdateControlVisibility(condition.Value);
        }

        private void AddToControls(IEnumerable<Widget> controls)
        {
            foreach (var control in controls)
            {
                this.AddControls(control.Controls);
            }
        }

        private void UpdateControlVisibility(bool conditionMet)
        {
            if (conditionMet)
            {
                this.IfControls.ForEach(x => x.Show());
                this.ElseControls.ForEach(x => x.Hide());
            }
            else
            {
                this.IfControls.ForEach(x => x.Hide());
                this.ElseControls.ForEach(x => x.Show());
            }
        }
    }
}
