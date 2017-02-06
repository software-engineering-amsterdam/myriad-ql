require 'parslet'

class Mini < Parslet::Parser
  # standard
  rule(:spaces) { match('\s').repeat(1) }
  rule(:spaces?) { spaces.maybe }


  # question
  rule(:string) { str('"') >> (str('"').absent? >> any).repeat.as(:string) >> str('"') }
  rule(:variable) { (str(':').absent? >> any).repeat.as(:variable) >> str(':') }
  rule(:type) { (str('boolean') | str('money')).as(:type) }
  rule(:field) { string >> spaces? >> variable >> spaces? >> type }

  root :field
end


def parse(str)
  mini = Mini.new

  mini.parse(str)
rescue Parslet::ParseFailed => failure
  puts failure
end

# p parse '"How much is?"' # => "1 + 2 + 3"@0
# p parse 'hasSoldHouse:'
p parse '"How much is?"

hasSoldHouse:      boolean'
# p parse 'hasSoldHouse: boolean'
# p parse "a + 2"      # fails, see below