using Questionnaires.ErrorHandling;
using System.Collections.Generic;

namespace Questionnaires.SemanticAnalysis
{
    public class TypeChecker
    {
        private QLContext Context;
        private Result result = new Result();

        public Result Analyze(QL.AST.INode node, QLContext context)
        {
            Context = context;
            List<Message> messages = new List<Message>();
            node.CheckSemantics(context, messages);

            foreach (var message in messages)
                result.AddEvent(message);

            return result;
        }
    }
}
