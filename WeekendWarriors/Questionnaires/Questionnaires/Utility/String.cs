using System.Collections.Generic;
using System.Linq;

namespace Questionnaires.Utility
{
    public class String
    {
        public static string TrimQuotes(string input)
        {
            if (input.First() == '"')
                input = input.Remove(0,1);
            if (input.Last() == '"')
                input = input.Remove(input.Length - 1,1);

            return input;
        }

        /* Utility function to format a collection of string into a single string
         * in the fomat <S1>, <S2>, ... and <Sx> */
        public static string FormatSequenceOfStrings(List<string> input)
        {
            if (input.Count == 0)
                return "";

            string combinedStrings = "";

            for (var index = 0; index < input.Count; index++)
            {
                var connector = ",";
                if (index == input.Count - 2)
                    connector = " and";
                if (index == input.Count - 1)
                    connector = "";

                combinedStrings = string.Format("{0} {1}{2} ", combinedStrings, input[index], connector);
            }

            return combinedStrings;
        }
    }
}
