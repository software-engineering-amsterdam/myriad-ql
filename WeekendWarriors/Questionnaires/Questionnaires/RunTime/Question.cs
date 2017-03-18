using System;
using System.Diagnostics;
using System.Windows.Controls;

namespace Questionnaires.RunTime
{
    public class Question
    {
        private Questionnaires.Renderer.Widgets.QuestionWidget Widget;
        private QL.AST.Question ASTNode;
        private Types.IType Value;

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

        public void SetWidget(QLS.AST.Widgets.Widget widget)
        {
            SetWidget(widget.CreateWidget((dynamic)Value));
        }

        public void Draw(Panel target)
        {
            target.Children.Add(Widget);
        }

        public void SetStyle(Renderer.Style.WidgetStyle style) // TODO: I think this can be removed once we properly refactor the style creation in the QLS processor
        {
            Widget.SetStyle(style);
        }

        public Types.IType GetValue()
        {
            return Value;
        }        

        private void SetWidget(Renderer.Widgets.QuestionWidget widget)
        {
            Widget = widget;
            Widget.SetLabel(ASTNode.Body);
            Widget.InputChanged += (sender, value) => SetValue(value);
        }

        public void SetValue(Types.IType value)
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
