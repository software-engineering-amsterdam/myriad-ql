require 'parslet'

module QL
  module Parser
    class FormTransformer < Parslet::Transform
      include AST

      # variable
      rule(variable: simple(:name)) { Variable.new(name) }

      # types
      rule(type: 'boolean') { BooleanType.new }
      rule(type: 'integer') { IntegerType.new }
      rule(type: 'money') { MoneyType.new }
      rule(type: 'string') { StringType.new }
      rule(type: 'decimal') { DecimalType.new }
      rule(type: 'date') { DateType.new }

      # literal
      rule(boolean_literal: simple(:value)) { BooleanLiteral.new(value.to_s) }
      rule(integer_literal: simple(:value)) { IntegerLiteral.new(value) }
      rule(string_literal: simple(:value)) { StringLiteral.new(value) }

      # question
      rule(question: { label: simple(:string_literal), id: simple(:variable), type: simple(:type) }) { Question.new(string_literal, variable, type) }
      rule(question: { label: simple(:string_literal), id: simple(:variable), type: simple(:type), assignment: subtree(:assignment) }) { ComputedQuestion.new(string_literal, variable, type, assignment) }

      # if statement
      rule(if_statement: { condition: subtree(:condition), body: subtree(:body) }) { IfStatement.new(condition, body) }
      rule(if_else_statement: { if_statement: { condition: subtree(:condition), body: subtree(:if_body) }, body: subtree(:else_body) }) { IfElseStatement.new(condition, if_body, else_body) }

      # form
      rule(form: { id: simple(:variable), body: subtree(:body) }) { Form.new(variable, body) }

      # expression
      rule(expression: subtree(:expression)) { ExpressionTransformer.new.apply(expression) }
    end
  end
end