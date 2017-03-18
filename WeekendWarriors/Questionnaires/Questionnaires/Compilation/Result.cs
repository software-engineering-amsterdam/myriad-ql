using System.Collections.Generic;

namespace Questionnaires.Compilation
{
    public class Result
    {
        private bool ContainsError = false;
        public List<Compilation.Message> Events { get; }

        public Result()
        {
            Events = new List<Compilation.Message>();
        }      

        public void AddEvent(Compilation.Message analysisEvent)
        {
            if (analysisEvent.IsError())
                ContainsError = true;

            Events.Add(analysisEvent);
        }

        public bool ContainsErrors()
        {
            return ContainsError;
        }

        public void Combine(Result result)
        {
            foreach (var analysisEvent in result.Events)
                AddEvent(analysisEvent);
        }
    }
}
