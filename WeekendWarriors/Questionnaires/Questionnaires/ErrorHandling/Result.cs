using System.Collections.Generic;

namespace Questionnaires.ErrorHandling
{
    public class Result
    {
        private bool ContainsError = false;
        public List<Message> Events { get; }

        public Result()
        {
            Events = new List<Message>();
        }      

        public void AddEvent(Message analysisEvent)
        {
            if (analysisEvent.IsError())
                ContainsError = true;

            Events.Add(analysisEvent);
        }

        public bool ContainsErrors()
        {
            return ContainsError;
        }
    }
}
