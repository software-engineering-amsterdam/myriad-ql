require_relative '../ast/type'
require_relative '../ast/expression'
require_relative '../ast/literal'
require_relative '../ast/statement'
require_relative '../ast/form'
require_relative '../ast/variable'
require 'parslet'

# parser for forms
class Parser < Parslet::Parser
  include ExpressionRules
  include TypeRules
  include LiteralRules
  include VariableRules
  include StatementRules
  include FormRules

  rule(:spaces) do
    match('\s').repeat(1)
  end

  rule(:spaces?) do
    spaces.maybe
  end

  root :form
end