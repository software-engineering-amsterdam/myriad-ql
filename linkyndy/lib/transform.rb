require 'parslet'
require_relative 'ast'

class Transform < Parslet::Transform
  rule(string: simple(:x)) { Ast::StringLiteral.new(x) }
  rule(integer: simple(:x)) { Ast::IntegerLiteral.new(x) }
  rule(boolean: simple(:x)) { Ast::BooleanLiteral.new(x) }

  rule(identifier: simple(:identifier)) { Ast::Identifier.new(identifier) }

  rule(left: subtree(:left), operator: '*', right: subtree(:right)) { Ast::Multiplication.new(left, right) }
  rule(left: subtree(:left), operator: '/', right: subtree(:right)) { Ast::Division.new(left, right) }
  rule(left: subtree(:left), operator: '+', right: subtree(:right)) { Ast::Addition.new(left, right) }
  rule(left: subtree(:left), operator: '-', right: subtree(:right)) { Ast::Subtraction.new(left, right) }

  rule(question: { string: simple(:string), type: simple(:type), identifier: simple(:identifier) }) { Ast::Question.new(string, Ast::Type.new(type), Ast::Identifier.new(identifier)) }
  rule(question: { string: simple(:string), type: simple(:type), identifier: simple(:identifier), value: subtree(:value) }) { Ast::Question.new(string, Ast::Type.new(type), Ast::Identifier.new(identifier), value) }

  rule(if_statement: { condition: subtree(:condition), if_true: subtree(:if_true) }) { Ast::IfStatement.new(condition, if_true) }
  rule(if_statement: { condition: subtree(:condition), if_true: subtree(:if_true), if_false: subtree(:if_false) }) { Ast::IfStatement.new(condition, if_true, if_false) }

  rule(form: { identifier: simple(:identifier), body: subtree(:body) }) { Ast::Form.new(Ast::Identifier.new(identifier), body) }
end
