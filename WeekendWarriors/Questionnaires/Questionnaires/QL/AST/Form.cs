using Questionnaires.Compilation;
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
            // Check all child nodes
            bool childNodesSemanticallyOk = true;
            foreach (var node in Statements)
            {
                if (!node.CheckSemantics(context, messages))
                    childNodesSemanticallyOk = false;
            }

            return childNodesSemanticallyOk;
        }
    }
}
