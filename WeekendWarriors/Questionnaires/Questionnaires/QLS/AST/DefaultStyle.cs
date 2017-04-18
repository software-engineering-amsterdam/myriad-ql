using System.Collections.Generic;
using Questionnaires.QL.AST.Types;
using Questionnaires.QLS.AST.Widgets;

namespace Questionnaires.QLS.AST
{
    public class DefaultStyle : INode
    {
        public IType Type { get; }
        public Dictionary<string, string> Properties { get; }
        public Widget Widget { get; }

        public DefaultStyle(IType type, Widget widget)
        {
            Properties = new Dictionary<string, string>();
            Type = type;
            Widget = widget;
        }

        public void AddWidgetProperty(Setting setting)
        {
            Properties[setting.Key] = setting.Value;
        }
    }
}
