require 'parslet'

# parser for forms
class Parser < Parslet::Parser
  rule(:spaces) { match('\s').repeat(1) }
  rule(:spaces?) { spaces.maybe }

  rule(:label) { str('"') >> (str('"').absent? >> any).repeat.as(:label) >> str('"') }
  rule(:variable) { (str(':').absent? >> any).repeat.as(:variable) >> str(':') }
  rule(:type) { (str('boolean') | str('money')).as(:type) }

  rule(:variable2) { match('\w+').repeat(1).as(:variable2) }
  rule(:operator) { (str('+') | str('-')).as(:operator) }
  rule(:expression) { str('(') >> (str(')').absent? >> (spaces? >> variable2 >> spaces? >> (operator >> spaces? >> variable2 >> spaces?).repeat) ).repeat.as(:expression) >> str(')') }

  rule(:question) { (spaces? >> label >> spaces? >> variable >> spaces? >> type >> spaces?).as(:question) }

  rule(:questions) { question.repeat.as(:questions) }

  # if block
  rule(:condition) { str('(') >> (str(')').absent? >> any).repeat.as(:condition) >> str(')') }
  rule(:block) { str('{') >> questions.as(:block) >> str('}') }
  rule(:if_statement) { (spaces? >> str('if') >> spaces? >> condition >> spaces? >> block >> spaces?).as(:if_statement) }

  root :question
end
