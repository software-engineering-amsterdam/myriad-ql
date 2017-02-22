using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DSL.AST;

namespace Tests.QL.SemanticAnalysis
{
    [TestClass]
    public class QuestionDefinition
    {
        protected SemanticTestHarness TestHarness = new SemanticTestHarness(ASTFactory.QLObjectType.Form);

        // TODO: this should induce a warning. We currently do not support warnings
        [TestMethod]
        public void TestDoubleDefinition()
        {
            string input = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    q1: ""This is the new q1"" boolean                    
                }
                ";
            TestHarness.TestExpression(input, 1, "Defining a question with a name that already exists in the current context");
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
            TestHarness.TestExpression(input, 1, "Defining a question with a name that already exists in the current context but with a different type");
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

            TestHarness.TestExpression(input, 1, "Referencing an undefined question");
        }
    }
}
