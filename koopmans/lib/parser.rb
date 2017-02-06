require 'parslet'

class Parser < Parslet::Parser
  # standard
  rule(:spaces) { match('\s').repeat(1) }
  rule(:spaces?) { spaces.maybe }


  # question
  rule(:label) { str('"') >> (str('"').absent? >> any).repeat.as(:label) >> str('"') }
  rule(:variable) { (str(':').absent? >> any).repeat.as(:variable) >> str(':') }
  rule(:type) { (str('boolean') | str('money')).as(:type) }
  rule(:question) { label >> spaces? >> variable >> spaces? >> type }

  root :question
end


# def parse(str)
#   parser = Parser.new
#   parser.parse(str)
# rescue Parslet::ParseFailed => failure
#   puts failure
# end

# p parse '"How much is?"' # => "1 + 2 + 3"@0
# p parse 'hasSoldHouse:'
# p parse '"How much is?"
#
# hasSoldHouse:      boolean'
# p parse 'hasSoldHouse: boolean'
# p parse "a + 2"      # fails, see below