require 'parslet'

# parser for forms
class Parser < Parslet::Parser
  rule(:spaces) { match('\s').repeat(1) }
  rule(:spaces?) { spaces.maybe }

  rule(:label) { str('"') >> (str('"').absent? >> any).repeat.as(:label) >> str('"') }
  rule(:variable) { (str(':').absent? >> any).repeat.as(:variable) >> str(':') }
  rule(:type) { (str('boolean') | str('money')).as(:type) }
  rule(:question) { (spaces? >> label >> spaces? >> variable >> spaces? >> type >> spaces?).as(:question) }

  rule(:questions) { question.repeat.as(:questions) }
  root :question
end
