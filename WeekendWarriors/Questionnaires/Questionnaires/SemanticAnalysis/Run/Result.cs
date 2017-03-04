using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis.Run
{
    public class Result
    {
        public Result()
        {
            Events = new List<SemanticAnalysis.Messages.Message>();
        }

        public List<SemanticAnalysis.Messages.Message> Events
        {
            get;
        }

        bool Error;

        public void AddEvent(Messages.Message analysisEvent)
        {
            if (analysisEvent.IsError())
                Error = true;

            Events.Add(analysisEvent);
        }

        public bool IsError()
        {
            return Error;
        }

        public void Combine(Result result)
        {
            foreach (var analysisEvent in result.Events)
                AddEvent(analysisEvent);
        }
    }
}
