using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Utility
{
    class Assertions
    {
        public static void AssertInRange<T>(T item, T[] range)
        {            
            var rangeAsList = new List<T>(range);
            Debug.Assert(rangeAsList.Contains(item));
        }
    }
}
