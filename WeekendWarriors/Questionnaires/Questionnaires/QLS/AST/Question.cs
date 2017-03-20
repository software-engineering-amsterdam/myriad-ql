namespace Questionnaires.QLS.AST
{
    public class Question : INode
    {
        public string Name { get; }

        public Question(string name)
        {
            Name = name;
        }

    }
}
