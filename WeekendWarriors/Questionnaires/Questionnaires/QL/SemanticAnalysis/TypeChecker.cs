using System.Collections.Generic;

namespace Questionnaires.SemanticAnalysis
{
    public class TypeChecker
    {
        private QLContext Context;
        private Compilation.Result result = new Compilation.Result();

        public Compilation.Result Analyze(QL.AST.INode node, QLContext context)
        {
            Context = context;
            List<Compilation.Message> messages = new List<Compilation.Message>();
            node.CheckSemantics(context, messages);

            foreach (var message in messages)
                result.AddEvent(message);

            return result;
        }
    }
}
