using Questionnaires.ErrorHandling;
using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QL.AST.Types;
using System.Collections.Generic;
using System;

namespace Questionnaires.QL.AST
{
    public class Conditional : IStatement
    {
        public Conditional(IExpression condition, List<IStatement> thenStatements, List<IStatement> elseStatements)
        {
            Condition = condition;
            ThenStatements = thenStatements;
            ElseStatements = elseStatements;
        }

        public IExpression Condition
        {
            get;
        }

        public List<IStatement> ThenStatements
        {
            get;
        }

        public List<IStatement> ElseStatements
        {
            get;
        }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            // Check the child nodes in the then and else branches for semantic errors
            if (!CheckChildNodeSemantics(context, messages))
                return false;

            // We only accept conditions of boolean types (this is not C)
            if (!ValidateConditionType((dynamic)Condition.GetResultType(context)))
            {
                messages.Add(new Error("Condition for conditional statement cannot be resolved to boolean"));
                return false;
            }
            return true;
        }

        private bool ValidateConditionType(BooleanType val)
        {
            return true;
        }

        private bool ValidateConditionType(IType val)
        {
            return false;
        }

        private bool CheckChildNodeSemantics(QLContext context, List<Message> messages)
        {
            var semanticsInChildNodesOk = true;

            foreach (var statement in ThenStatements)
            {
                if (!statement.CheckSemantics(context, messages))
                    semanticsInChildNodesOk = false;
            }

            foreach (var statement in ElseStatements)
            {
                if (!statement.CheckSemantics(context, messages))
                    semanticsInChildNodesOk = false;
            }

            if (!Condition.CheckSemantics(context, messages))
                semanticsInChildNodesOk = false;

            return semanticsInChildNodesOk;
        }

        public void GetDependencies(Dictionary<Question, HashSet<Identifier>> dependencies)
        {
            foreach (var statement in ThenStatements)
            {
                statement.GetDependencies(dependencies);                
            }

            foreach (var statement in ElseStatements)
            {
                statement.GetDependencies(dependencies);
            }
        }
    }
}
