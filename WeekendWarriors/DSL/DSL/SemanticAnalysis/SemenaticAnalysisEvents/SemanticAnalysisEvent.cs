using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.SemanticAnalysis.SemenaticAnalysisEvents
{
    public class SemanticAnalysisEvent : ISemenaticAnalysisEvent
    {
        protected string Message;
        public SemanticAnalysisEvent(string messsage)
        {
            this.Message = messsage;
        }

        public override string ToString()
        {
            return Message;
        }
    }
}
