using Questionnaires.Value;
using Questionnaires.QL.AST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Operators = Questionnaires.QL.AST.Operators;
using Literals = Questionnaires.QL.AST.Literals;

namespace Questionnaires.ExpressionEvaluator
{
    public class Evaluator
    {
        protected VariableStore.VariableStore VarialbeStore;

        public Evaluator(VariableStore.VariableStore store)
        {
            this.VarialbeStore = store;
        }

        public IType Evaluate(IExpression expression)
        {
            return Visit((dynamic)expression);
        }

        private IType Visit(Operators.Positive node)
        {
            IType operand = Visit((dynamic)node);
            return operand.Positive();
        }

        public IType Visit(Operators.Negative node)
        {
            IType operand = Visit((dynamic)node);
            return operand.Negative();
        }

        public Value.IType Visit(Operators.Bang node)
        {
            IType operand = Visit((dynamic)node);
            return operand.Bang();
        }

        public Value.IType Visit(Literals.Money node)
        {
            return new MoneyType(node.Value);
        }

        public Value.IType Visit(Literals.String node)
        {
            return new StringType(node.Value); 
        }

        public Value.IType Visit(Literals.Number node)
        {
            return new IntegerType(node.Value);
        }

        public Value.IType Visit(Literals.Boolean node)
        {
            return new BooleanType(node.Value);
        }

        public Value.IType Visit(Identifier node)
        {
            return this.VarialbeStore.GetValue(node.Name);
        }

        public Value.IType Visit(Operators.And node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.And(rightHandSideValue);
        }

        public Value.IType Visit(Operators.Or node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Or(rightHandSideValue);
        }

        public Value.IType Visit(Operators.Addition node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Add(rightHandSideValue);
        }

        public Value.IType Visit(Operators.Subtraction node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Subtract(rightHandSideValue);
        }

        public Value.IType Visit(Operators.Division node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Divide(rightHandSideValue);
        }

        public Value.IType Visit(Operators.Multiply node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Multiply(rightHandSideValue);
        }

        public Value.IType Visit(Operators.GreaterThan node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.GreaterThan(rightHandSideValue);
        }

        public Value.IType Visit(Operators.GreaterThanOrEqual node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.GreaterThanOrEqual(rightHandSideValue);
        }

        public Value.IType Visit(Operators.LessThan node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.LessThan(rightHandSideValue);
        }
        
        public Value.IType Visit(Operators.LessThanOrEqual node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.LessThanOrEqual(rightHandSideValue);
        }
        
        public Value.IType Visit(Operators.Inequal node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.InequalTo(rightHandSideValue);
        }
        
        public Value.IType Visit(Operators.Equal node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.EqualTo(rightHandSideValue);
        }
    }
}
