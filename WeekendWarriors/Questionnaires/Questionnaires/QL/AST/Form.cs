using Questionnaires.ErrorHandling;
using Questionnaires.SemanticAnalysis;
using System;
using System.Collections.Generic;

namespace Questionnaires.QL.AST
{
    public class Form : INode
    {
        public Form(string identifier, List<IStatement> statements)
        {
            Identifier = identifier;
            Statements = statements;
        }

        public List<IStatement> Statements
        {
            get;
        }

        public String Identifier
        {
            get;
        }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            return Statements.TrueForAll((statement) =>
            {
                return statement.CheckSemantics(context, messages);
            });
        }
    }
}
