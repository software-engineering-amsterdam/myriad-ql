require 'parslet'
require 'require_all'

require_rel '../ast'

module QL
  module Parser

    # parser for forms
    class Parser < Parslet::Parser
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
      rule(:form) { spaces? >> (str('form') >> spaces? >> variable >> spaces? >> block).as(:form) }

      # literal
      rule(:boolean_literal) { (str('true') | str('false')).as(:boolean) >> spaces? }
      rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer) >> spaces? }
      rule(:string_literal) { str('"') >> match('[^"]').repeat.as(:string) >> str('"') >> spaces? }

      # statement
      rule(:assignment?) { (str('=') >> spaces? >> expression).maybe >> spaces? }
      rule(:question) { (string_literal >> variable_assignment >> type >> assignment?).as(:question) >> spaces? }
      rule(:block) { str('{') >> spaces? >> (question | if_statement).repeat.as(:block) >> str('}') >> spaces? }
      rule(:if_statement) { (str('if') >> spaces? >> expression >> block).as(:if_statement) }

      # type
      rule(:type) { (str('boolean') | str('string') | str('integer') | str('decimal') | str('date') | str('money')).as(:type) >> spaces? }

      # variable
      rule(:variable) { match('\w+').repeat(1).as(:variable) }
      rule(:variable_assignment) { variable >> str(':') >> spaces? }

      root(:form)
    end
  end
end