using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.AST;
using DSL.AST.Operators;

namespace DSL.SemanticAnalysis
{
    class Analyzer
    {
        private Dictionary<string, QLType> IdentifiertoType = new Dictionary<string, QLType>();
        // TODO: injection
        private ExpressionValidator validator = new ExpressionValidator();

        public Analyzer()
        {
            validator.InvalidExpression += Validator_InvalidExpression;
        }

        /* TODO: The way this is now does not make much sense. The original idea was to have the 
         * ExpressionValidator throw errors solely based on the expression. This object would then 
         * be able to link the error message to the source code location (which we would have to
         * include in the node objects). 
         */
        private void Validator_InvalidExpression(object sender, InvalidExpressionEventArgs e)
        {
            OnSemanticError(new SemanticErrorArgs(e.Message));
        }

        public void Analyze(AST.INode node)
        {
            Visit((dynamic)node);
        } 
        
        protected QLType Visit(QLForm node)
        {
            foreach (var statement in node.Statements)
                Visit((dynamic)statement);

            return QLType.None;
        }

        protected QLType Visit(QLQuestion node)
        {
            // Store the type of this identifier
            IdentifiertoType[node.Identifier] = node.Type;

            return validator.Evaluate(node);
        }

        protected QLType Visit(QLComputedQuestion node)
        {
            QLType assigneeType = Visit((dynamic)node.Question);
            QLType assignorType = Visit((dynamic)node.Expression);

            return validator.Evaluate(node, assigneeType, assignorType);               
        }

        protected QLType Visit(QLConditional node)
        {
            QLType conditionType = Visit((dynamic)node.Condition);

            foreach (var statement in node.ThenStatements)
                Visit((dynamic)statement);
            foreach (var statement in node.ElseStatements)
                Visit((dynamic)statement);

            return validator.Evaluate(node, conditionType);            
        }

        protected QLType Visit(QLArithmeticOperation node)
        {          
            QLType lhsType = Visit((dynamic)node.Lhs);
            QLType rhsType = Visit((dynamic)node.Rhs);

            return validator.Evaluate(node, lhsType, rhsType);
        }

        protected QLType Visit(QLComparisonOperation node)
        {
            QLType lhsType = Visit((dynamic)node.Lhs);
            QLType rhsType = Visit((dynamic)node.Rhs);

            return validator.Evaluate(node, lhsType, rhsType);
        }

        protected QLType Visit(QLEqualityOperation node)
        {
            QLType lhsType = Visit((dynamic)node.Lhs);
            QLType rhsType = Visit((dynamic)node.Rhs);

            return validator.Evaluate(node, lhsType, rhsType);
        }

        protected QLType Visit(QLUnaryOperation node)
        {
            QLType operandType = Visit((dynamic)node.Operand);

            return validator.Evaluate(node, operandType);
        }

        protected QLType Visit(QLBoolean node)
        {
            return QLType.Bool;
        }
            
        protected QLType Visit(QLMoney node)
        {
            return QLType.Money;
        }   

        protected QLType Visit(QLNumber node)
        {
            return QLType.Number;
        }

        protected QLType Visit(QLString node)
        {
            return QLType.String;
        }

        protected QLType Visit(QLIdentifier node)
        {
            if (IdentifiertoType.ContainsKey(node.Name))
            {
                return IdentifiertoType[node.Name];
            }
            else
            {
                /* TODO: double check if there is any way in which we can get here
                 * before we get to the question node that introduces the identifier.
                 * Since it is not specified we will take the old school/easy way and
                 * only allow usage of a variable after declaration */

                OnSemanticError(new SemanticErrorArgs("Encountered undefined Identifier \"" + node.Name));
            }
            
            return QLType.None;
        }

        public delegate void SemanticErrorEventHandler(object sender, SemanticErrorArgs e);
        public event SemanticErrorEventHandler SemanticError;

        protected virtual void OnSemanticError(SemanticErrorArgs e)
        {
            if (SemanticError != null)
                SemanticError(this, e);
        }

    }
}
