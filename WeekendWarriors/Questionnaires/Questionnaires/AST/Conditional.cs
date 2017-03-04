using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;
using System.Diagnostics;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Value;

namespace Questionnaires.AST
{
    public class Conditional : INode
    {
        public Conditional(IExpression condition, List<INode> thenStatements, List<INode> elseStatements)
        {
            this.Condition = condition;
            this.ThenStatements = thenStatements;
            this.ElseStatements = elseStatements;
        }

        public IExpression Condition
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

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            // Check the child nodes in the then and else branches for semantic errors
            if (!CheckChildNodeSemantics(context, messages))
                return false;

            // We only accept conditions of boolean types (this is not C)
            if(!ValidateConditionType((dynamic)Condition.GetResultType(context)))
            {
                messages.Add(new Error("Condition for conditional statement cannot be resolved to boolean"));
                return false;
            }
            return true;
        }

        private bool ValidateConditionType(BoolValue val)
        {
            return true;
        }

        private bool ValidateConditionType(IValue val)
        {
            return false;
        }

        private bool CheckChildNodeSemantics(QLContext context, List<Message> messages)
        {
            bool semanticsInChildNdodesOk = true;

            foreach (var statement in ThenStatements)
            {
                if (!statement.CheckSemantics(context, messages))
                    semanticsInChildNdodesOk = false;
            }

            foreach (var statement in ElseStatements)
            {
                if (!statement.CheckSemantics(context, messages))
                    semanticsInChildNdodesOk = false;
            }

            if (!Condition.CheckSemantics(context, messages))
                semanticsInChildNdodesOk = false;

            return semanticsInChildNdodesOk;
        }
    }
}
