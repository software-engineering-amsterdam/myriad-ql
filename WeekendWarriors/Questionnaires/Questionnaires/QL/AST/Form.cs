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
            foreach (var statement in Statements)
            {
                statement.GetDependencies(dependencies);
            }

            var checker = new DependencyChecker();
            foreach (var dependency in dependencies)
            {
                checker.AddDependencies(dependency.Key, dependency.Value);
            }

            checker.Check(messages);

            return true;
        }
    }
}
