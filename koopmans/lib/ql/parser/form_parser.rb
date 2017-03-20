require 'parslet'

module QL
  module Parser
    class FormParser < Parslet::Parser
      include ExpressionParser

      root(:form)

      # spaces, breaks, tabs
      rule(:_) { match('\s').repeat(1).maybe }

      # literals
      rule(:boolean_literal) { (str('true') | str('false')).as(:boolean_literal) >> _ }
      rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer_literal) >> _ }
      rule(:string_literal)  { str('"') >> match('[^"]').repeat.as(:string_literal) >> str('"') >> _ }

      # variable
      rule(:variable)            { match('\w+').repeat(1).as(:variable) }
      rule(:variable_assignment) { variable.as(:id) >> str(':') >> _ }

      # types
      rule(:type) { (str('boolean') | str('integer') | str('integer') | str('decimal') | str('date') | str('money')).as(:type) >> _ }

      # statement
      rule(:assignment?)       { (str('=') >> _ >> expression.as(:assignment)).maybe >> _ }
      rule(:question)          { (string_literal.as(:label) >> variable_assignment >> type.as(:type) >> assignment?).as(:question) >> _ }
      rule(:body)              { _ >> (question | if_else_statement | if_statement).repeat.as(:body) }
      rule(:if_statement)      { (str('if') >> _ >> expression.as(:condition) >> str('{') >> body >> str('}')).as(:if_statement) >> _ }
      rule(:if_else_statement) { (if_statement >> _ >> str('else') >> _ >> str('{') >> body >> str('}')).as(:if_else_statement) >> _ }

      # form
      rule(:form) { _ >> (str('form') >> _ >> variable.as(:id) >> _ >> str('{') >> body >> str('}')).as(:form) }
    end
  end
end
