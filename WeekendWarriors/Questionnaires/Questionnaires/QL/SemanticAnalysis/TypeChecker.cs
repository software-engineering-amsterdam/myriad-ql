using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.QL.AST;
using Questionnaires.QL.AST.Operators;
using Questionnaires.QL.AST.Visitor;

namespace Questionnaires.SemanticAnalysis
{
    public class TypeChecker
    {
        private QLContext Context;
        private Compilation.Result result = new Compilation.Result();

        public Compilation.Result Analyze(QL.AST.INode node, QLContext context)
        {
            this.Context = context;
            List<Compilation.Message> messages = new List<Compilation.Message>();
            node.CheckSemantics(context, messages);

            foreach(var message in messages)
                result.AddEvent(message);
            
            return result;
        }
    }
}
