from pyparsing import *

'''
Define our grammar

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

}

'''

colon = ':'
lcurly = '{'
rcurly = '}'
dquote = '"'
word = Word(alphas)
field_type = oneOf('CharField BoolField')
form_type = oneOf('form')

field_name = word
field_display = QuotedString('"')
form_name = word

field = field_display + field_name + colon + field_type
form = form_type + form_name + lcurly + OneOrMore(field) + rcurly
