using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.RunTime
{
    class Question
    {
        public Questionnaires.Renderer.Widgets.QuestionWidget Widget { get; set; }

        public Question(string identifier, string body, Questionnaires.Renderer.Widgets.QuestionWidget widget)
        {
            this.Identifier = identifier;
            this.Body = body;
            Widget = widget;
        }

        public string Identifier
        {
            get;
        }

        public string Body
        {
            get;
        }
    }
}
