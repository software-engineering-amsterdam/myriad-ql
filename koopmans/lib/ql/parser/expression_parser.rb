require 'parslet'

module QL
  module Parser
    module ExpressionParser
      include Parslet

      # operators
      rule(:multiplication_operator)    { (str('*') | str('/')).as(:arithmetic_operator) >> _ }
      rule(:addition_operator)          { (str('+') | str('-')).as(:arithmetic_operator) >> _ }
      rule(:comparison_equals_operator) { (str('==') | str('!=')).as(:comparison_equals_operator) >> _ }
      rule(:comparison_order_operator)  { (str('<=') | str('>=') | str('<') | str('>')).as(:comparison_order_operator) >> _ }
      rule(:boolean_operator)           { (str('&&') | str('||')).as(:boolean_operator) >> _ }
      rule(:negation_operator)          { (str('!') | str('-')).as(:negation_operator) }

      # expression
      rule(:expression)                        { boolean_expression.as(:expression) }
      rule(:expression_with_parenthesis)       { str('(') >> _ >> expression >> str(')') }
      rule(:boolean_expression)                { comparison_order_expression.as(:left) >> (boolean_operator >> comparison_order_expression.as(:right)).repeat(1) | comparison_order_expression }
      rule(:comparison_order_expression)       { comparison_equals_expression.as(:left) >> (comparison_order_operator >> comparison_equals_expression.as(:right)).repeat(1) | comparison_equals_expression }
      rule(:comparison_equals_expression)      { addition_expression.as(:left) >> (comparison_equals_operator >> addition_expression.as(:right)).repeat(1) | addition_expression }
      rule(:addition_expression)               { multiplication_expression.as(:left) >> (addition_operator >> multiplication_expression.as(:right)).repeat(1) | multiplication_expression }
      rule(:multiplication_expression)         { negation_expression.as(:left) >> (multiplication_operator >> negation_expression.as(:right)).repeat(1) | negation_expression }
      rule(:negation_expression)               { (negation_operator >> literal_or_variable_or_expression.as(:single)).repeat(1) | literal_or_variable_or_expression }
      rule(:literal_or_variable_or_expression) { (boolean_literal | integer_literal | string_literal | variable | expression_with_parenthesis) >> _ }
    end
  end
end
