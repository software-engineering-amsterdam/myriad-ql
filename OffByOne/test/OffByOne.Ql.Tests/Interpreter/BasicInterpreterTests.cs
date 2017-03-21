namespace OffByOne.Ql.Tests.Interpreter
{
    using System.Threading.Tasks;
    using System.Windows;

    using OffByOne.Ql.Tests.Common;
    using OffByOne.Ql.Tests.Interpreter.Base;
    using OffByOne.Ql.Values;

    using Xunit;

    public class BasicInterpreterTests : InterpreterTest
    {
        [Fact]
        public async Task Interpretation_ShouldReturnCorrectNumberOfControls()
        {
            await ThreadHelper.StartSTATask(() =>
            {
                var input = @"
                form taxOfficeExample { 
                    ""Did you sell a house in 2010?""
                        hasSoldHouse: boolean
                    ""Did you buy a house in 2010?""
                        hasBoughtHouse: boolean
                    ""Did you enter a loan?""
                        hasMaintLoan: boolean
    
                    if (hasSoldHouse) {
                        ""What was the selling price?""
                            sellingPrice: money
                        ""Private debts for the sold house:""
                            privateDebt: money
                        ""Value residue:""
                            valueResidue: money = 
                                (sellingPrice - privateDebt)
                        }
                    }
                ";
                var form = this.GetInterpretationFromInput(input);
                Assert.True(form.Controls.Count == 12);
            });
        }

        [Fact]
        public async Task Interpretation_ShouldHideAndShowControlsOnUpdate()
        {
            await ThreadHelper.StartSTATask(() =>
            {
                var input = @"
                form taxOfficeExample { 
                    ""Did you sell a house in 2010?""
                        hasSoldHouse: boolean
                    ""Did you buy a house in 2010?""
                        hasBoughtHouse: boolean
                    ""Did you enter a loan?""
                        hasMaintLoan: boolean
    
                    if (hasSoldHouse) {
                        ""What was the selling price?""
                            sellingPrice: money
                        ""Private debts for the sold house:""
                            privateDebt: money
                        ""Value residue:""
                            valueResidue: money = 
                                (sellingPrice - privateDebt)
                        }
                    }
                ";
                var form = this.GetInterpretationFromInput(input);
                Assert.False(form.Controls["sellingPrice"].Visibility == Visibility.Visible);
                Assert.False(form.Controls["privateDebt"].Visibility == Visibility.Visible);
                Assert.False(form.Controls["valueResidue"].Visibility == Visibility.Visible);

                form.Environment.UpdateValues("hasSoldHouse", new BooleanValue(true));
                Assert.True(form.Controls["sellingPrice"].Visibility == Visibility.Visible);
                Assert.True(form.Controls["privateDebt"].Visibility == Visibility.Visible);
                Assert.True(form.Controls["valueResidue"].Visibility == Visibility.Visible);
            });
        }
    }
}
