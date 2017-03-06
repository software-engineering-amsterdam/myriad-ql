require 'parslet'

module QL
  module Parser
    # parser for forms
    class Parser < Parslet::Parser
      root(:form)

      # spaces
      rule(:spaces) { match('\s').repeat(1) }
      rule(:spaces?) { spaces.maybe }

      # expression
      rule(:integer_negation?) { str('-').as(:integer_negation).maybe }
      rule(:boolean_negation?) { str('!').as(:boolean_negation).maybe }
      rule(:negation?) { (str('!') | str('-')).as(:negation).maybe }
      rule(:variable_or_literal) { (boolean_negation? >> boolean_literal | integer_negation? >> integer_literal | string_literal | negation? >> variable) >> spaces? }
      rule(:calculation) { variable_or_literal.as(:left) >> operator >> expression.as(:right) }
      rule(:operator) { (str('-') | str('+') | str('*') | str('/') | str('<=') | str('>=') | str('==') | str('!=') | str('<') | str('>') | str('&&') | str('||') | str('!')).as(:operator) >> spaces? }
      rule(:expression) { str('(') >> spaces? >> expression.as(:expression) >> spaces? >> str(')') >> spaces? | calculation | variable_or_literal }

      # form
      rule(:form) { spaces? >> (str('form') >> spaces? >> variable.as(:id) >> spaces? >> block).as(:form) }

      # literal
      rule(:boolean_literal) { (str('true') | str('false')).as(:boolean_literal) >> spaces? }
      rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer_literal) >> spaces? }
      rule(:string_literal) { str('"') >> match('[^"]').repeat.as(:string_literal) >> str('"') >> spaces? }

      # statement
      rule(:assignment?) { (str('=') >> spaces? >> expression).maybe >> spaces? }
      rule(:question) { (string_literal.as(:label) >> variable_assignment >> type.as(:type) >> assignment?).as(:question) >> spaces? }
      rule(:block) { str('{') >> spaces? >> (question | if_statement).repeat.as(:block) >> str('}') >> spaces? }
      rule(:if_statement) { (str('if') >> spaces? >> expression >> block).as(:if_statement) }

      # type
      rule(:boolean_type) { str('boolean').as(:type) }
      rule(:string_type) { str('string').as(:type) }
      rule(:integer_type) { str('integer').as(:type) }
      rule(:decimal_type) { str('decimal').as(:type) }
      rule(:date_type) { str('date').as(:type) }
      rule(:money_type) { str('money').as(:type) }

      rule(:type) { (boolean_type | string_type | integer_type | decimal_type | date_type | money_type) >> spaces? }

      # variable.as
      rule(:variable) { match('\w+').repeat(1).as(:variable) }
      rule(:variable_assignment) { variable.as(:id) >> str(':') >> spaces? }
    end
  end
end