using System;

namespace Questionnaires.QLS.AST.Widgets
{
    public abstract class Widget : INode
    {
        public virtual Questionnaires.UI.Widgets.QuestionWidget CreateWidget(Types.IType type)
        {
            throw new NotSupportedException();
        }

        public virtual Questionnaires.UI.Widgets.QuestionWidget CreateWidget(Types.BooleanType type)
        {
            throw new NotSupportedException();
        }

        public virtual Questionnaires.UI.Widgets.QuestionWidget CreateWidget(Types.StringType type)
        {
            throw new NotSupportedException();
        }

        public virtual Questionnaires.UI.Widgets.QuestionWidget CreateWidget(Types.IntegerType type)
        {
            throw new NotSupportedException();
        }

        public virtual Questionnaires.UI.Widgets.QuestionWidget CreateWidget(Types.MoneyType type)
        {
            throw new NotSupportedException();
        }
    }
}
