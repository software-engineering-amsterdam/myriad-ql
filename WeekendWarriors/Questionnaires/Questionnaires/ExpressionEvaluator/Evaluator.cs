using Questionnaires.Value;
using Questionnaires.AST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Operators = Questionnaires.AST.Operators;
using Literals = Questionnaires.AST.Literals;

namespace Questionnaires.ExpressionEvaluator
{
    public class Evaluator
    {
        protected VariableStore.VariableStore VarialbeStore;

        public Evaluator(VariableStore.VariableStore store)
        {
            this.VarialbeStore = store;
        }

        public IValue Evaluate(IExpression expression)
        {
            return Visit((dynamic)expression);
        }

        private IValue Visit(Operators.Positive node)
        {
            IValue operand = Visit((dynamic)node);
            return operand.Positive();
        }

        public IValue Visit(Operators.Negative node)
        {
            IValue operand = Visit((dynamic)node);
            return operand.Negative();
        }

        public Value.IValue Visit(Operators.Bang node)
        {
            IValue operand = Visit((dynamic)node);
            return operand.Bang();
        }

        public Value.IValue Visit(Literals.Money node)
        {
            return new DecimalValue(node.Value);
        }

        public Value.IValue Visit(AST.Literals.String node)
        {
            return new StringValue(node.Value); 
        }

        public Value.IValue Visit(Literals.Number node)
        {
            return new IntValue(node.Value);
        }

        public Value.IValue Visit(AST.Literals.Boolean node)
        {
            return new BoolValue(node.Value);
        }

        public Value.IValue Visit(Identifier node)
        {
            return this.VarialbeStore.GetValue(node.Name);
        }

        public Value.IValue Visit(Operators.And node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.And(rightHandSideValue);
        }

        public Value.IValue Visit(Operators.Or node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Or(rightHandSideValue);
        }

        public Value.IValue Visit(Operators.Addition node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Add(rightHandSideValue);
        }

        public Value.IValue Visit(Operators.Subtraction node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Subtract(rightHandSideValue);
        }

        public Value.IValue Visit(Operators.Division node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Divide(rightHandSideValue);
        }

        public Value.IValue Visit(Operators.Multiply node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.Multiply(rightHandSideValue);
        }

        public Value.IValue Visit(Operators.GreaterThan node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.GreaterThan(rightHandSideValue);
        }

        public Value.IValue Visit(Operators.GreaterThanOrEqual node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.GreaterThanOrEqual(rightHandSideValue);
        }

        public Value.IValue Visit(Operators.LessThan node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.LessThan(rightHandSideValue);
        }
        
        public Value.IValue Visit(Operators.LessThanOrEqual node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.LessThanOrEqual(rightHandSideValue);
        }
        
        public Value.IValue Visit(Operators.Inequal node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.InequalTo(rightHandSideValue);
        }
        
        public Value.IValue Visit(Operators.Equal node)
        {
            var leftHandSideValue = Visit((dynamic)node.Lhs);
            var rightHandSideValue = Visit((dynamic)node.Rhs);
            return leftHandSideValue.EqualTo(rightHandSideValue);
        }
    }
}
