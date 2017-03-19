using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Utility
{
    class String
    {
        public static void TrimQuotes(string input)
        {
            if (input.First() == '"')
                input.Remove(0);
            if (input.Last() == '"')
                input.Remove(input.Length -1);
        }
    }
}
