namespace OffByOne.Ql.Evaluator
{
    using System.Windows;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Evaluator.Controls.Questions;
    using OffByOne.Ql.Visitors;

    public class QlEvaluator : BaseQlVisitor<UIElement>
    {
        public override UIElement Visit(FormStatement form)
        {
            var questionnaire = new Window();
            questionnaire.Title = form.Identifier;
            var list = new ListView();
            foreach (var statement in form.Statements)
            {
                list.Items.Add(statement.Accept(this));
            }

            questionnaire.Content = list;
            questionnaire.Show();
            return questionnaire;
        }

        public override UIElement Visit(QuestionStatement statement)
        {
            var factory = new ControlFactory();
            return factory.CreateControl(statement).Control;
        }
    }
}
