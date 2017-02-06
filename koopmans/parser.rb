require 'parslet'

class Mini < Parslet::Parser
  rule(:integer) { match('[0-9]').repeat(1) }
  root(:integer)
end

Mini.new.parse("132432")  # => "132432"@0