using Questionnaires.QL.AST.Types;
using Questionnaires.UI.Widgets;
using System;

namespace Questionnaires.QLS.AST.Widgets
{
    public abstract class Widget : INode
    {
        public virtual QuestionWidget CreateWidget(IType type)
        {
            throw new NotSupportedException();
        }

        public virtual QuestionWidget CreateWidget(BooleanType type)
        {
            throw new NotSupportedException();
        }

        public virtual QuestionWidget CreateWidget(StringType type)
        {
            throw new NotSupportedException();
        }

        public virtual QuestionWidget CreateWidget(IntegerType type)
        {
            throw new NotSupportedException();
        }

        public virtual QuestionWidget CreateWidget(MoneyType type)
        {
            throw new NotSupportedException();
        }
    }
}
