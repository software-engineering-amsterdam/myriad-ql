using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents
{
    public abstract class SemanticAnalysisEvent : ISemenaticAnalysisEvent
    {
        private string Message;

        public SemanticAnalysisEvent(string messsage)
        {
            this.Message = messsage;
        }

        public abstract bool IsError();

        public override string ToString()
        {
            return Message;
        }
    }
}
