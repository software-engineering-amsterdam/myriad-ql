using System.Collections.Generic;

namespace Questionnaires.Compilation
{
    public class Result
    {
        public Result()
        {
            Events = new List<Compilation.Message>();
        }

        public List<Compilation.Message> Events
        {
            get;
        }

        bool Error;

        public void AddEvent(Compilation.Message analysisEvent)
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
