using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.SemanticAnalysis
{
    [TestClass]
    public class QuestionDefinition
    {
        protected SemanticTestHarness TestHarness = new SemanticTestHarness();

        [TestMethod]
        public void TestDoubleDefinition()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    q1: ""This is the new q1"" boolean                    
                }
                ";
            TestHarness.TestForm(input, 1, "Defining a question with a name that already exists in the current context");
        }

        [TestMethod]
        public void TestDoubleDefinitionDifferentType()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    q1: ""This is the new q1"" money                    
                }
                ";
            TestHarness.TestForm(input, 1, "Defining a question with a name that already exists in the current context but with a different type");
        }

        [TestMethod]
        public void TestReferenceToUndefinedQuestion()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    if(q2)
                    {
                        q3: ""This is q3"" boolean
                    }
                }
                ";

            TestHarness.TestForm(input, 1, "Referencing an undefined question");
        }

        [TestMethod]
        public void TestSameLabelInMultipleQuestionsSameType()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    q2: ""This is q1"" boolean                    
                }
                ";
            TestHarness.TestForm(input, 1, "Defining a question with a body that is used for another question");
        }

        [TestMethod]
        public void TestSameLabelInMultipleQuestionsDifferentType()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    q2: ""This is q1"" money                    
                }
                ";
            TestHarness.TestForm(input, 1, "Defining a question with a body that is used for another question");
        }

        [TestMethod]
        public void testCyclicDependencySimple()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean(q2)
                    q2: ""This is q2"" boolean(q1)                    
                }
                ";
            TestHarness.TestForm(input, 2, "Defining two questions that depend on each other");
        }

        [TestMethod]
        public void testCyclicDependencyComplex()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" money(q2 + q3)
                    q2: ""This is q2"" money       
                    q3: ""This is q3"" money(q4 + 1.23) 
                    q4: ""This is q4"" money(q5 * q6)
                    q5: ""This is q5"" money       
                    q6: ""This is q6"" money(q1)                  
                }
                ";
            TestHarness.TestForm(input, 4, "Defining four questions that indirectly depend on each other");
        }
    }
}
