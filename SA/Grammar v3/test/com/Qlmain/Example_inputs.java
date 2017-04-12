package com.Qlmain;

/**
 * Created by sotos on 9/4/2017.
 */
public class Example_inputs {

    private String wrongTestInput;
    private String correctTestInput;
    public Example_inputs() {
        wrongTestInput = "form taxOfficeExample { \n" +
                "  \"Did you sell a house in 2010?\"\n" +
                "    hasSoldHouse: boolean\n" +
                "  \"Did you buy a house in 2010?\"\n" +
                "    hasBoughtHouse: boolean\n" +
                "  \"Did you enter a loan?\"\n" +
                "    hasMainLoan: string\n" +
                "\n" +
                "  if (hasSoldHouse AND hasBoughtHouse OR true) {\n" +
                "    \"What was the selling price?\"\n" +
                "      sellingPrice: money\n" +
                "       if ( (sellingPrice > 1) AND (privateDebt == sellingPric) ) {\n"+
                "           \"What was the selling price?\"\n"+
                "            sellingPrices: money\n" +
                "            \"Private debts for the sold house:\"\n"+
                "            privateDebts: money\n"+
                "            \"Value residue:\"\n"+
                "            valueResidues: money = (sellingPrices+1.1)*10 - 100.0\n"+
                "        }\n"+
                "    \"Private debts for the sold house:\"\n" +
                "      privateDebt: money\n" +
                "    \"Value residue:\"\n" +
                "      valueResidue: money = \n" +
                "        (sellingPrice - 11)\n" +
                "  }\n" +
                "\n" +
                "}";

        correctTestInput = "form taxOfficeExample { \n" +
                "  \"Did you sell a house in 2010?\"\n" +
                "    hasSoldHouse: boolean\n" +
                "  \"Did you buy a house in 2010?\"\n" +
                "    hasBoughtHouse: boolean\n" +
                "  \"Did you enter a loan?\"\n" +
                "    hasMainLoan: string\n" +
                "\n" +
                "  if (hasSoldHouse AND hasBoughtHouse OR true) {\n" +
                "    \"What was the selling price?\"\n" +
                "      sellingPrice: money\n" +
                "    \"Private debts for the sold house:\"\n" +
                "      privateDebt: money\n" +
                "    \"Value residue:\"\n" +
                "      valueResidue: money = \n" +
                "        (sellingPrice - 11.0)\n" +
                "  }\n" +
                "  if ( (sellingPrice > 1.0) AND (privateDebt == sellingPrice) ) { "+
                "    \"What was the selling price?\"\n"+
                "    sellingPrices: money\n" +
                "    \"Private debts for the sold house:\"\n"+
                "    privateDebts: money\n"+
                "    \"Value residue:\"\n"+
                "    valueResidues: money = (sellingPrices+1.1)*10.0 - 100.0\n"+
                "  }\n"+
                "\n" +
                "}";
    }

    public String getCorrectTestInput() {
        return correctTestInput;
    }

    public String getWrongTestInput() {
        return wrongTestInput;
    }
}
