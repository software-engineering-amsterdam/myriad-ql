using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST;
using System.Collections.Generic;

namespace Questionnaires.QL.SemanticAnalysis
{
    public class TypeChecker
    {
        private QLContext Context;
        private Result Result;

        public TypeChecker(Result result)
        {
            Result = result;
        }

        public void Analyze(INode node, QLContext context)
        {
            Context = context;
            List<Message> messages = new List<Message>();
            node.CheckSemantics(context, messages);

            foreach (var message in messages)
                Result.AddEvent(message);
        }
    }
}
