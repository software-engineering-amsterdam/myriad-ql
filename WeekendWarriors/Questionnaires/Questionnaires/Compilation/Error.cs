namespace Questionnaires.Compilation
{
    class Error : Message
    {
        public Error(string message) : base("Error: " + message)
        {

        }

        public override bool IsError()
        {
            return true;
        }
    }
}
