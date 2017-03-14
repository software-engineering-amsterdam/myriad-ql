namespace OffByOne.Runner
{
    using System;
    using System.IO;
    using System.Windows;

    using OffByOne.Runner.Builder;

    public class Program : Application
    {
        private static string sampleQlInput = @"
                form questionnaire { 
                    ""Is this a question?""
                        existentialism: boolean

                    ""When will this be finished?""
                        deadline: date

                    ""What is your favourite decimal number?""
                        favDecimal: decimal

                    ""Answer the following equation: 5 + 2 * 3""
                        equation: integer

                    ""How much money do you have?"" 
                        account: money

                    ""String two:""
                        stringtwo: string

                    if(equation == 11){
                        ""Do you like pie?""
                            pie: boolean (existentialism)
                    } else {
                        ""Some numbers""
                            comments: decimal (favDecimal + equation)
                    }

                    if (x){ 
                        ""Y?"" 
                            y: boolean 
                    }

                    if (y) { 
                        ""X?"" 
                            x: boolean 
                    }
                }
            ";

        private static string sampleQlsInput = @"
                stylesheet taxOfficeExample
                  page Housing {
                    section ""Buying"" {
                      question hasBoughtHouse  
                        widget checkbox 
                    }
                    section ""Loaning"" {
                      question hasMaintLoan
                    }
                    section ""Loaning"" {
                      question hasMaintLoan
                    }
                  } 
                  page Selling { 
                    section ""Selling"" {
                      question hasSoldHouse
                        widget radio(""Yes"", ""No"") 
                      section ""You sold a house"" {
                        question sellingPrice
                          widget spinbox
                        question privateDebt
                          widget spinbox 
                        question valueResidue
                        default money {
                          width: 400
                          font: ""Arial"" 
                          fontsize: 14
                          color: #999999
                          widget spinbox
                        }        
                      }
                    }
                    default boolean widget radio(""Yes"", ""No"")
                  }";

        [STAThread]
        public static void Main(string[] args)
        {
            var qlCode = File.ReadAllText(Path.GetFullPath(@"..\..\LanguageSamples\structure.ql"));
            var qlsCode = File.ReadAllText(Path.GetFullPath(@"..\..\LanguageSamples\style.qls"));

            var qlBuilder = new QlLanguageBuilder(sampleQlInput);
            var qlsBuilder = new QlsLanguageBuilder(sampleQlsInput);
            var appRunner = new AppRunner();
            appRunner.Run(qlBuilder);
            ////appRunner.Run(qlBuilder, qlsBuilder);

            Console.WriteLine("Press any key to continue...");
            Console.ReadKey();
        }
    }
}
