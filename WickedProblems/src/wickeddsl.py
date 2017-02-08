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

colon = Suppress(':')
word = Word(alphas)
field_type = oneOf('boolean money')
form_type = oneOf('form')
evaluation = 'if'
condition = QuotedString(quoteChar="(", endQuoteChar=")", escChar='\\')

codeblock_unquoted = QuotedString(quoteChar="{", endQuoteChar="}", escChar='\\')
codeblock_quoted = QuotedString(quoteChar="{", endQuoteChar="}", escChar='\\', unquoteResults=False)

field_name = word
field_display = QuotedString('"')
form_name = word


question = field_display + field_name + colon + field_type
evaluate = evaluation + condition + codeblock_quoted
field = question | evaluate

form_outer = form_type + form_name + codeblock_unquoted
form_inner = OneOrMore(field)
