using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.AST;

namespace Tests.QL.SemanticAnalysis
{
    [TestClass]
    public class Scoping
    {
        /*
         * Note that usage of questions oustide of the scope is allowed in QL
         * There is no real scoping like there is in general purpose programming languages.
         * The scoping does have effect on the value of the question. In the example below for instance
         * q2 will have the value 'undefined' when q1 is false. When q1 is true, q2 can be either true
         * or false:
         * form myForm{
         *  q1: ""This is q1"" boolean
         *  if(q1)
         *  {
         *      q2: ""This is q2"" boolean
         *  }
         *} 
         */

        protected SemanticTestHarness TestHarness = new SemanticTestHarness();

        [TestMethod]
        public void TestScopingNormal()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    if(q1)
                    {
                        q2: ""This is q2"" boolean
                    }
                }
                ";
            TestHarness.TestForm(input, 0, "Normal conditional question");
        }

        [TestMethod]
        public void TestUseOutsideOfIfScope()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    if(q1)
                    {
                        q2: ""This is q2"" boolean
                    }
                    if(q2)
                    {
                        q3: ""This is q3"" boolean
                    }
                }
                ";
            TestHarness.TestForm(input, 0, "Using a question outside of its scope");
        }

        [TestMethod]
        public void TestUseOutsideScope()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    if(q1)
                    {
                        q2: ""This is q2"" boolean
                    }
                    else
                    {
                        q3: ""This is q3"" boolean
                    }

                    /* Both of the values used here are unreachable */
                    q4: ""This is q4"" boolean(q3)
                    q5: ""This is q5"" boolean(q2)
                }
                ";
            TestHarness.TestForm(input, 0, "Using a question outside of its scope");
        }
    }
}
