using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Compilation
{
    public abstract class Message
    {
        private string Content;

        public Message(string messsage)
        {
            this.Content = messsage;
        }

        public abstract bool IsError();

        public override string ToString()
        {
            return Content;
        }
    }
}
