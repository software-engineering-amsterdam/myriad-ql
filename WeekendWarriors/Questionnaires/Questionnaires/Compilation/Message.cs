namespace Questionnaires.Compilation
{
    public abstract class Message
    {
        private string Content;

        public Message(string messsage)
        {
            Content = messsage;
        }

        public abstract bool IsError();

        public override string ToString()
        {
            return Content;
        }
    }
}
