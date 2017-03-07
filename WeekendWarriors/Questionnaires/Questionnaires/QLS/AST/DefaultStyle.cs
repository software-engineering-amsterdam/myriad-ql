using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.AST
{
    class DefaultStyle : INode
    {
        public Questionnaires.Types.IType Type { get; }
        public Dictionary<string, string> Properties { get; }
        public Questionnaires.Renderer.Widgets.QuestionWidget Widget { get; }

        public DefaultStyle(Questionnaires.Types.IType type, Questionnaires.Renderer.Widgets.QuestionWidget widget)
        {
            this.Properties = new Dictionary<string, string>();
            this.Type = type;
            this.Widget = widget;
        }

        public void AddWidgetProperty(Setting setting)
        {
            this.Properties[setting.Key] = setting.Value;
        }
    }
}
