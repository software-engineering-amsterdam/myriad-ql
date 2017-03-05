using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.AST;
using Questionnaires.AST.Operators;
using Questionnaires.AST.Visitor;

namespace Questionnaires.SemanticAnalysis
{
    public class TypeChecker
    {
        private QLContext Context;
        private Result result = new Result();

        public Result Analyze(AST.INode node, QLContext context)
        {
            this.Context = context;
            List<SemanticAnalysis.Messages.Message> messages = new List<Messages.Message>();
            node.CheckSemantics(context, messages);

            foreach(var message in messages)
                result.AddEvent(message);
            
            return result;
        }
    }
}
