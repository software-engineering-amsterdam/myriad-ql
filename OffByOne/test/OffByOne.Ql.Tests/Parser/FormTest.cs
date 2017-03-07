namespace OffByOne.Ql.Tests.Parser
{
    using System.Linq;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Tests.Parser.Base;
    using OffByOne.Ql.Values;

    using Xunit;

    public class FormTest : ParserTest
    {
        [Fact]
        public void AstCreation_ShouldReturnFormNodeIfGivenSyntaxIsCorrect()
        {
            var astTree = this.GetAstNodesFromInput(@"
                form questionnaire { 
                    ""What is your birth date?"" 
                        birthDate: date

                    ""Do you want to continue?""
                        continue: boolean

                    if (birthDate < '31-12-1999' || continue) {
                        ""How much money do you spend on alcoholic beverages?""
                            alcoholicBeverages: money
                    } else {
                        ""Okay. Goodbye?""
                            exit: boolean(continue || false)
                    }
                }
            ");

            Assert.IsType<FormStatement>(astTree);

            var castAstTree = (FormStatement)astTree;

            Assert.Equal("questionnaire", castAstTree.Identifier);
            Assert.Equal(3, castAstTree.Statements.Count());

            var questions = castAstTree
                .Statements
                .OfType<QuestionStatement>()
                .ToList();

            var birthString = new StringValue("What is your birth date?");
            var continueString = new StringValue("Do you want to continue?");
            Assert.True(questions.Any(x => x.Identifier == "birthDate" && x.Label.Value == birthString));
            Assert.True(questions.Any(x => x.Identifier == "continue" && x.Label.Value == continueString));

            var ifStatement = castAstTree.Statements.OfType<IfStatement>().First();
            Assert.True(ifStatement.Condition is OrExpression);
            var condition = (OrExpression)ifStatement.Condition;

            Assert.IsType<LessThanExpression>(condition.LeftExpression);
            Assert.IsType<VariableExpression>(condition.RightExpression);

            var lessThanExp = (LessThanExpression)condition.LeftExpression;
            var variableExp = (VariableExpression)condition.RightExpression;

            Assert.IsType<VariableExpression>(lessThanExp.LeftExpression);
            Assert.IsAssignableFrom<Expression>(lessThanExp.RightExpression);
            Assert.Equal(variableExp.Identifier, "continue");

            var lhs = (VariableExpression)lessThanExp.LeftExpression;
            var rhs = (Expression)lessThanExp.RightExpression;
            Assert.IsAssignableFrom<DateLiteral>(rhs);

            Assert.Equal(lhs.Identifier, "birthDate");

            var elseStatements = ifStatement.ElseStatements;
            Assert.Equal(1, elseStatements.Count());

            Assert.IsType<QuestionStatement>(elseStatements.First());
            var computedQuestion = (QuestionStatement)elseStatements.First();

            Assert.IsType<OrExpression>(computedQuestion.ComputedValue);

            var computedValue = (OrExpression)computedQuestion.ComputedValue;
            Assert.IsType<VariableExpression>(computedValue.LeftExpression);
            Assert.IsAssignableFrom<Expression>(computedValue.RightExpression);

            var computedRhs = (Expression)computedValue.RightExpression;
            Assert.IsType<BooleanLiteral>(computedRhs);
        }

        [Fact]
        public void AstCreation_ShouldReturnExpressionInRightOrder()
        {
            var astTree = this.GetAstNodesFromInput(@"
                form questionnaire { 
                    if (2 + 3 * 4 < someVar && 3 / 1 * 2 != 6) {
                        ""Is this a question?""
                            existentialism: boolean
                    }
                }
            ");

            Assert.IsType<FormStatement>(astTree);
            var castAstTree = (FormStatement)astTree;

            var ifStatement = castAstTree.Statements.OfType<IfStatement>().First();

            Assert.IsType<AndExpression>(ifStatement.Condition);
            var condition = (AndExpression)ifStatement.Condition;

            Assert.IsType<LessThanExpression>(condition.LeftExpression);
            {
                var leftCondition = (LessThanExpression)condition.LeftExpression;
                Assert.IsType<AddExpression>(leftCondition.LeftExpression);
                var leftLhs = (AddExpression)leftCondition.LeftExpression;

                Assert.IsType<MultiplyExpression>(leftLhs.RightExpression);
            }

            Assert.IsType<NotEqualExpression>(condition.RightExpression);
            {
                var rightCondition = (NotEqualExpression)condition.RightExpression;
                Assert.IsType<MultiplyExpression>(rightCondition.LeftExpression);
                var rightLhs = (MultiplyExpression)rightCondition.LeftExpression;

                Assert.IsType<DivideExpression>(rightLhs.LeftExpression);
            }

            // TODO: Test that leafs of expressions are Literals (they are null until refactored)
        }

        [Fact]
        public void AstCreation_ShouldContainSourceCode()
        {
            var astTree = this.GetAstNodesFromInput(@"
                form questionnaire { 
                    if (2 + 3 * 4 < someVar && 3 / 1 * 2 != 6) {
                        ""Is this a question?""
                            existentialism: boolean
                    }
                }
            ");

            Assert.IsType<FormStatement>(astTree);
            var castAstTree = (FormStatement)astTree;

            Assert.True(castAstTree.Statements.All(x => x.SourceCode != null));
        }
    }
}
