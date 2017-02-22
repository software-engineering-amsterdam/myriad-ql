using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLConditional : INode
    {
        public QLConditional(IQLExpression condition, List<INode> thenStatements, List<INode> elseStatements)
        {
            this.Condition = condition;
            this.ThenStatements = thenStatements;
            this.ElseStatements = elseStatements;
        }

        public IQLExpression Condition
        {
            get;
        }

        public List<INode> ThenStatements
        {
            get;
        }

        public List<INode> ElseStatements
        {
            get;
        }

        public bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            bool childrenValid = ValidateChildred(ref warnings, ref errors);
            if (!childrenValid)
                return false;

            // Conditional is valid if the condition is of type boolean
            if(Condition.GetQLType() != QLType.Bool)
            {
                errors.Add("Condition for conditional statement cannot be resolved to boolean");
                return false;
            }

            return true;
        }

        private bool ValidateChildred(ref List<string> warnings, ref List<string> errors)
        {
            bool childrenValid = true;

            foreach (var statement in ThenStatements)
            {
                if (!statement.Validate(ref warnings, ref errors))
                    childrenValid = false;
            }
            foreach (var statement in ElseStatements)
            {
                if (!statement.Validate(ref warnings, ref errors))
                    childrenValid = false;
            }
            if (!Condition.Validate(ref warnings, ref errors))
                childrenValid = false;

            return childrenValid;
        }
    }
}
