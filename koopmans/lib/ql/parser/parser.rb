require 'parslet'

module QL
  module Parser
    # parser for forms
    class Parser < Parslet::Parser
      root(:form)

      root :comparison_order

      # rule(:root) {
      #   ((exp | addition)).repeat
      # }

      rule(:exp) {
        str('(') >> comparison_order >> str(')')
      }


      rule(:comparison_order) {
        equals.as(:left) >> (comparison_order_op >> equals.as(:right)).repeat(1) |
          equals
      }

      rule(:equals) {
        addition.as(:left) >> (equal_op >> addition.as(:right)).repeat(1) |
          addition
      }

      rule(:addition) {
        multiplication.as(:left) >> (add_op >> multiplication.as(:right)).repeat(1) |
          multiplication
      }

      rule(:multiplication) {
        i_or_exp.as(:left) >> (mult_op >> i_or_exp.as(:right)).repeat(1) |
          i_or_exp }


      rule(:i_or_exp) {
        (integer_literal | exp)
      }

      rule(:mult_op) { (str('*') | str('/')).as(:operator) >> _ }
      rule(:add_op) { (str('+') | str('-')).as(:operator) >> _ }
      rule(:equal_op) { (str('==') | str('!=')).as(:operator) >> _ }
      rule(:comparison_order_op) { (str('<=') | str('>=') | str('<') | str('>')).as(:operator) >> _ }


      # spaces, breaks
      rule(:_) { match('\s').repeat(1).maybe }

      # expression
      rule(:negation?) { (str('!') | str('-')).as(:negation).maybe }
      rule(:variable_or_literal) { negation? >> (boolean_literal | integer_literal | string_literal | variable) >> _ }
      rule(:calculation) { variable_or_literal.as(:left) >> operator >> expression.as(:right) }
      rule(:operator) { (str('-') | str('+') | str('*') | str('/') | str('<=') | str('>=') | str('==') | str('!=') | str('<') | str('>') | str('&&') | str('||') | str('!')).as(:operator) >> _ }
      rule(:expression) { str('(') >> _ >> expression.as(:expression) >> _ >> str(')') >> _ | calculation | variable_or_literal }

      # form
      rule(:form) { _ >> (str('form') >> _ >> variable.as(:id) >> _ >> str('{') >> body >> str('}')).as(:form) }

      # literal
      rule(:boolean_literal) { (str('true') | str('false')).as(:boolean_literal) >> _ }
      rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer_literal) >> _ }
      rule(:string_literal) { str('"') >> match('[^"]').repeat.as(:string_literal) >> str('"') >> _ }

      # statement
      rule(:assignment?) { (str('=') >> _ >> expression).maybe >> _ }
      rule(:question) { (string_literal.as(:label) >> variable_assignment >> type.as(:type) >> assignment?).as(:question) >> _ }
      rule(:body) { _ >> (question | if_statement).repeat.as(:body) }
      rule(:if_statement) { (str('if') >> _ >> expression >> str('{') >> body >> str('}')).as(:if_statement) >> _ }

      # type
      rule(:type) { (str('boolean') | str('integer') | str('integer') | str('decimal') | str('date') | str('money')).as(:type) >> _ }

      # variable.as
      rule(:variable) { match('\w+').repeat(1).as(:variable) }
      rule(:variable_assignment) { variable.as(:id) >> str(':') >> _ }
    end
  end
end