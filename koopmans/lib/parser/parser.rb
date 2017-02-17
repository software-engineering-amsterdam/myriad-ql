require 'parslet'

# parser for forms
class Parslet::Parser
  rule(:spaces) do
    match('\s').repeat(1)
  end

  rule(:spaces?) do
    spaces.maybe
  end

  root :form
end