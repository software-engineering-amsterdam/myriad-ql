using Questionnaires.QL.AST;
using Questionnaires.QL.AST.Literals;
using Questionnaires.QL.AST.Operators;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.RunTime
{
    public class ExpressionEvaluator
    {
        private QuestionStore QuestionStore;

        public ExpressionEvaluator(QuestionStore questionStore)
        {
            QuestionStore = questionStore;
        }

        public IType Evaluate(IExpression expression)
        {
            return Visit((dynamic)expression);
        }

        private IType Visit(Positive node)
        {
            IType operand = Visit((dynamic)node.Operand);
            return operand.Positive();
        }

        public IType Visit(Negative node)
        {
            IType operand = Visit((dynamic)node.Operand);
            return operand.Negative();
        }

        public IType Visit(Bang node)
        {
            IType operand = Visit((dynamic)node.Operand);
            return operand.Bang();
        }

        public IType Visit(Money node)
        {
            return new MoneyType(node.Value);
        }

        public IType Visit(String node)
        {
            return new StringType(node.Value);
        }

        public IType Visit(Number node)
        {
            return new IntegerType(node.Value);
        }

        public IType Visit(Boolean node)
        {
            return new BooleanType(node.Value);
        }

        public IType Visit(Identifier node)
        {
            return QuestionStore.GetValue(node.Name);
        }

        public IType Visit(And node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.And(rightHandSideValue);
        }

        public IType Visit(Or node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Or(rightHandSideValue);
        }

        public IType Visit(Addition node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Add(rightHandSideValue);
        }

        public IType Visit(Subtraction node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Subtract(rightHandSideValue);
        }

        public IType Visit(Division node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Divide(rightHandSideValue);
        }

        public IType Visit(Multiply node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Multiply(rightHandSideValue);
        }

        public IType Visit(GreaterThan node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.GreaterThan(rightHandSideValue);
        }

        public IType Visit(GreaterThanOrEqual node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.GreaterThanOrEqual(rightHandSideValue);
        }

        public IType Visit(LessThan node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.LessThan(rightHandSideValue);
        }

        public IType Visit(LessThanOrEqual node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.LessThanOrEqual(rightHandSideValue);
        }

        public IType Visit(Inequal node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.InequalTo(rightHandSideValue);
        }

        public IType Visit(Equal node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.EqualTo(rightHandSideValue);
        }
    }
}
