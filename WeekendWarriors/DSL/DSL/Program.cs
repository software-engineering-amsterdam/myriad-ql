using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


using Newtonsoft.Json;

namespace DSL
{
    class Program
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args)
        {
            string inputString = @"
                form Box1HouseOwning {
            hasSoldHouse: ""Did you sell a house in 2010?"" boolean
            hasBoughtHouse: ""Did you by a house in 2010?"" boolean
            hasMaintLoan: ""Did you enter a loan for maintenance / reconstruction ? ""
boolean
            if (hasSoldHouse == true)
            {
                sellingPrice: ""Price the house was sold for:"" money(0.00)
                privateDebt: ""Private debts for the sold house:"" money(1200)
                
            }
            else{
                valueResidue: ""Value residue:"" money (sellingPrice - privateDebt)
            }
        }";

            var form = AST.FormFactory.Create(inputString);
            Console.WriteLine(Newtonsoft.Json.JsonConvert.SerializeObject(form, Formatting.Indented));
                
        }
    }
}
