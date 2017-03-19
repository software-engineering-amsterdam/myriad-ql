using Questionnaires.ErrorHandling;
using Questionnaires.QL.SemanticAnalysis;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;

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

        public bool CheckDependencies(Result messages)
        {
            var dependencies = new Dictionary<Question, HashSet<Identifier>>();
            foreach(var statement in Statements)
            {
                statement.GetDependencies(dependencies);
            }

            DependencyChecker checker = new DependencyChecker();            
            foreach(var kvp in dependencies)
            {
                checker.AddDependencies(kvp.Key, kvp.Value);
            }

            checker.Check(messages);

            return true;
        }
    }
}
