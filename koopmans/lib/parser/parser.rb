require_relative '../ast/type'
require_relative '../ast/expression'
require_relative '../ast/literal'
require_relative '../ast/statement'
require_relative '../ast/form'
require_relative '../ast/variable'
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