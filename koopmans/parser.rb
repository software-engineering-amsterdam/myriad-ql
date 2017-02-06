require 'parslet'

class Mini < Parslet::Parser
  rule :string do
    str('"') >> (str('"').absent? >> any).repeat.as(:question) >> str('"')
  end

  root :string
end

def parse(str)
  mini = Mini.new

  mini.parse(str)
rescue Parslet::ParseFailed => failure
  puts failure
end

p parse '"How much is?"' # => "1 + 2 + 3"@0
# p parse "a + 2"      # fails, see below