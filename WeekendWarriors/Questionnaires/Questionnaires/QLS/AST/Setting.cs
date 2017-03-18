namespace Questionnaires.QLS.AST
{
    public class Setting : INode
    {
        public string Key { get; }
        public string Value { get; }
        public Setting(string key, string value)
        {
            Key = key;
            Value = value;
        }
    }
}
