namespace Questionnaires.Compilation
{
    public class Warning : Message
    {
        public Warning(string message) : base("Warning: " + message)
        {

        }

        public override bool IsError()
        {
            return false;
        }
    }
}
