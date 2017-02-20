using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.AST;

namespace Tests.QL.SemanticAnalysis
{
    [TestClass]
    public class Scoping
    {
        protected SemanticTestHarness TestHarness = new SemanticTestHarness(ASTFactory.QLObjectType.Form);

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
            TestHarness.TestExpression(input, 0, "Normal conditional question");
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
            TestHarness.TestExpression(input, 1, "Using a question outside of its scope");
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
            TestHarness.TestExpression(input, 2, "Using a question outside of its scope");
        }
    }
}
