require 'parslet'

module QL
  module Parser
    # parser for forms
    class Parser < Parslet::Parser
      root(:form)

      # spaces, breaks, tabs
      rule(:_) { match('\s').repeat(1).maybe }

      # literals
      rule(:boolean_literal) { (str('true') | str('false')).as(:boolean_literal) >> _ }
      rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer_literal) >> _ }
      rule(:string_literal) { str('"') >> match('[^"]').repeat.as(:string_literal) >> str('"') >> _ }

      # variable
      rule(:variable) { match('\w+').repeat(1).as(:variable) }
      rule(:variable_assignment) { variable.as(:id) >> str(':') >> _ }

      # types
      rule(:type) { (str('boolean') | str('integer') | str('integer') | str('decimal') | str('date') | str('money')).as(:type) >> _ }

      # operators
      rule(:multiplication_operator) { (str('*') | str('/')).as(:operator) >> _ }
      rule(:addition_operator) { (str('+') | str('-')).as(:operator) >> _ }
      rule(:comparison_equals_operator) { (str('==') | str('!=')).as(:operator) >> _ }
      rule(:comparison_order_operator) { (str('<=') | str('>=') | str('<') | str('>')).as(:operator) >> _ }
      rule(:boolean_operator) { (str('&&') | str('||')).as(:operator) >> _ }
      rule(:negation_operator) { (str('!') | str('-')).as(:operator) >> _ }

      # expression
      # precedence order: booolean, order, equals, addition, multiplication, literal
      rule(:expression) { boolean_expression.as(:expression) }
      rule(:expression_with_parenthesis) { str('(') >> _ >> expression >> str(')') }
      rule(:boolean_expression) { comparison_order_expression.as(:left) >> (boolean_operator >> comparison_order_expression.as(:right)).repeat(1) | comparison_order_expression }
      rule(:comparison_order_expression) { comparison_equals_expression.as(:left) >> (comparison_order_operator >> comparison_equals_expression.as(:right)).repeat(1) | comparison_equals_expression }
      rule(:comparison_equals_expression) { addition_expression.as(:left) >> (comparison_equals_operator >> addition_expression.as(:right)).repeat(1) | addition_expression }
      rule(:addition_expression) { multiplication_expression.as(:left) >> (addition_operator >> multiplication_expression.as(:right)).repeat(1) | multiplication_expression }
      rule(:multiplication_expression) { negation_expression.as(:left) >> (multiplication_operator >> negation_expression.as(:right)).repeat(1) | negation_expression }
      rule(:negation_expression) { (negation_operator >> literal_or_variable_or_expression.as(:single)).repeat(1) | literal_or_variable_or_expression }
      rule(:literal_or_variable_or_expression) { (boolean_literal | integer_literal | string_literal | variable | expression_with_parenthesis) >> _ }

      # statement
      rule(:assignment?) { (str('=') >> _ >> expression.as(:assignment)).maybe >> _ }
      rule(:question) { (string_literal.as(:label) >> variable_assignment >> type.as(:type) >> assignment?).as(:question) >> _ }
      rule(:body) { _ >> (question.repeat(1) | if_statement.repeat(1)).repeat.as(:body) }
      rule(:if_statement) { (str('if') >> _ >> expression.as(:condition) >> str('{') >> body >> str('}')).as(:if_statement) >> _ }

      # form
      rule(:form) { _ >> (str('form') >> _ >> variable.as(:id) >> _ >> str('{') >> body >> str('}')).as(:form) }
    end
  end
end