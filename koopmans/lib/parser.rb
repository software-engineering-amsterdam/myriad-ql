require 'parslet'

# parser for forms
class Parser < Parslet::Parser
  rule(:spaces) { match('\s').repeat(1) }
  rule(:spaces?) { spaces.maybe }

  rule(:label) { str('"') >> (str('"').absent? >> any).repeat.as(:label) >> str('"') }
  rule(:variable) { (str(':').absent? >> any).repeat.as(:variable) >> str(':') }
  rule(:type) { (str('boolean') | str('money')).as(:type) }
  rule(:question) { label >> spaces? >> variable >> spaces? >> type }

  root :question
end
