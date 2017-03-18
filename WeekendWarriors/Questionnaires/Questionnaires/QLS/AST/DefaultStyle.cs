using System.Collections.Generic;

namespace Questionnaires.QLS.AST
{
    public class DefaultStyle : INode
    {
        public Questionnaires.Types.IType Type { get; }
        public Dictionary<string, string> Properties { get; }
        public Widgets.Widget Widget { get; }

        public DefaultStyle(Questionnaires.Types.IType type, Widgets.Widget widget)
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
