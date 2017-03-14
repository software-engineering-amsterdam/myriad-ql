module Prophet
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

    rule(if_statement: { condition: subtree(:condition), true_branch: subtree(:true_branch) }) { Ast::IfStatement.new(condition, true_branch) }
    rule(if_statement: { condition: subtree(:condition), true_branch: subtree(:true_branch), false_branch: subtree(:false_branch) }) { Ast::IfElseStatement.new(condition, true_branch, false_branch) }

    rule(form: { identifier: simple(:identifier), body: subtree(:body) }) { Ast::Form.new(Ast::Identifier.new(identifier), body) }
  end
end
