require 'parslet'
require_relative 'ast'

class Transform < Parslet::Transform
  rule(text: simple(:x)) { Ast::TextLiteral.new(x) }
  rule(number: simple(:x)) { Ast::NumberLiteral.new(x) }
  rule(bool: simple(:x)) { Ast::BoolLiteral.new(x) }

  rule(identifier: simple(:identifier)) { Ast::Identifier.new(identifier) }

  rule(left: subtree(:left), operator: '*', right: subtree(:right)) { Ast::Multiplication.new(left, right) }
  rule(left: subtree(:left), operator: '/', right: subtree(:right)) { Ast::Division.new(left, right) }
  rule(left: subtree(:left), operator: '+', right: subtree(:right)) { Ast::Addition.new(left, right) }
  rule(left: subtree(:left), operator: '-', right: subtree(:right)) { Ast::Subtraction.new(left, right) }

  rule(question: { text: simple(:text), type: simple(:type), identifier: simple(:identifier) }) { Ast::Question.new(text, Ast::Type.new(type), Ast::Identifier.new(identifier)) }
  rule(question: { text: simple(:text), type: simple(:type), identifier: simple(:identifier), value: subtree(:value) }) { Ast::Question.new(text, Ast::Type.new(type), Ast::Identifier.new(identifier), value) }

  rule(if_statement: { condition: subtree(:condition), if_true: subtree(:if_true) }) { Ast::IfStatement.new(condition, if_true) }
  rule(if_statement: { condition: subtree(:condition), if_true: subtree(:if_true), if_false: subtree(:if_false) }) { Ast::IfStatement.new(condition, if_true, if_false) }

  rule(form: { identifier: simple(:identifier), body: subtree(:body) }) { Ast::Form.new(Ast::Identifier.new(identifier), body) }
end
