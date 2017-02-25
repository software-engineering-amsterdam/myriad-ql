using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;

namespace Questionnaires.SemanticAnalysis.Run
{
    public class Result : IResult
    {
        public Result()
        {
            Events = new List<ISemenaticAnalysisEvent>();
        }

        public List<ISemenaticAnalysisEvent> Events
        {
            get;
        }

        bool Error;

        public void AddEvent(ISemenaticAnalysisEvent analysisEvent)
        {
            if (analysisEvent.IsError())
                Error = true;

            Events.Add(analysisEvent);
        }

        public bool IsError()
        {
            return Error;
        }

        public void Combine(IResult result)
        {
            foreach (var analysisEvent in result.Events)
                AddEvent(analysisEvent);
        }
    }
}
