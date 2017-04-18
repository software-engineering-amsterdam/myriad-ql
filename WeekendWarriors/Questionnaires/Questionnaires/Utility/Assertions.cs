using System.Collections.Generic;
using System.Diagnostics;

namespace Questionnaires.Utility
{
    public class Assertions
    {
        public static void AssertInRange<T>(T item, T[] range)
        {            
            var rangeAsList = new List<T>(range);
            Debug.Assert(rangeAsList.Contains(item));
        }
    }
}
