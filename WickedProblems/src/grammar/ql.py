'''
form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean

  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }
  "Is this questions being printed?"
    isPrinted: boolean
}
'''

'''
BNF
<form> ::= <type> <identifier> '{' <blockBody> '}'
<blockBody> ::= <question> | <conditional>
<conditional> ::= 'if(' <exp> ')' '{' <blockBody> '}'
<question> ::= <label> <variable> ':' <dataType>
<type> ::= <Ident>
<identifier> ::= <Ident>
<dataType> ::= <boolean>|<money>
<boolean> ::= true | false





'''