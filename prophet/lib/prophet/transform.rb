module Prophet
  class Transform < Parslet::Transform
    rule(text: simple(:x))    { Ast::TextLiteral.new(x) }
    rule(number: simple(:x))  { Ast::NumberLiteral.new(x) }
    rule(bool: simple(:x))    { Ast::BoolLiteral.new(x) }

    rule(type: 'text')    { Ast::TextType.new }
    rule(type: 'number')  { Ast::NumberType.new }
    rule(type: 'bool')    { Ast::BoolType.new }

    rule(identifier: simple(:identifier)) { Ast::Identifier.new(identifier) }

    {
      '&&' => Ast::LogicalAnd,
      '||' => Ast::LogicalOr,
      '==' => Ast::Equal,
      '!=' => Ast::NotEqual,
      '<=' => Ast::LessThenOrEqual,
      '<'  => Ast::LessThen,
      '>'  => Ast::GreaterThen,
      '>=' => Ast::GreaterThenOrEqual,
      '+'  => Ast::Addition,
      '-'  => Ast::Subtraction,
      '*'  => Ast::Multiplication,
      '/'  => Ast::Division
    }.each do |operator, ast_class|
      rule(left: subtree(:left), operator: operator, right: subtree(:right)) do
        ast_class.new(left, right)
      end
    end

    rule(operator: '!', value: subtree(:value)) { Ast::Negation.new(value) }

    rule(question: { text: simple(:text), type: simple(:type), identifier: simple(:identifier) }) do
      Ast::Question.new(text, type, identifier)
    end
    rule(question: { text: simple(:text), type: simple(:type), identifier: simple(:identifier), value: subtree(:value) }) do
      Ast::QuestionWithValue.new(text, type, identifier, value)
    end

    rule(if_statement: { condition: subtree(:condition), true_branch: subtree(:true_branch) }) do
      Ast::IfStatement.new(condition, true_branch)
    end
    rule(if_statement: { condition: subtree(:condition), true_branch: subtree(:true_branch), false_branch: subtree(:false_branch) }) do
      Ast::IfElseStatement.new(condition, true_branch, false_branch)
    end

    rule(form: { identifier: simple(:identifier), body: subtree(:body) }) do
      Ast::Form.new(identifier, body)
    end
  end
end
