using Questionnaires.QL.AST.Types;
using Questionnaires.QLS.AST.Widgets;
using Questionnaires.UI.Widgets;
using Questionnaires.UI.Widgets.Style;
using System;
using System.Diagnostics;
using System.Windows.Controls;

namespace Questionnaires.RunTime
{
    public class Question
    {
        private QuestionWidget Widget;
        private QL.AST.Question ASTNode;
        private IType Value;

        public Question(QL.AST.Question questionASTNode)
        {
            ASTNode = questionASTNode;
            SetWidget(questionASTNode.Type.GetWidget());
            Value = ASTNode.Type;
        }

        public string Identifier
        {
            get { return ASTNode.Identifier; }
        }

        public void SetWidget(Widget widget)
        {
            SetWidget(widget.CreateWidget((dynamic)Value));
        }

        public void Draw(Panel target)
        {
            target.Children.Add(Widget);
        }

        public void SetStyle(WidgetStyle style)
        {
            Widget.SetStyle(style);
        }

        public IType GetValue()
        {
            return Value;
        }

        private void SetWidget(QuestionWidget widget)
        {
            Widget = widget;
            Widget.SetLabel(ASTNode.Body);
            Widget.InputChanged += (sender, value) => SetValue(value);
        }

        public void SetValue(IType value)
        {
            Debug.Assert(value.GetType() == ASTNode.Type.GetType());

            bool valueChanged = Value.InequalTo(value).GetValue();
            Value = value;
            if (valueChanged)
            {
                Widget.SetQuestionValue(Value);
                OnValueChanged(new EventArgs());
            }
        }

        public void SetVisibility(bool visible)
        {
            Widget.SetVisibility(visible);
        }

        public event EventHandler ValueChanged;
        private void OnValueChanged(EventArgs e)
        {
            if (ValueChanged != null)
                ValueChanged(this, e);
        }
    }
}
