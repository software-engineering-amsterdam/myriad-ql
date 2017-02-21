require 'parslet'
require 'require_all'

require_rel '../ast'

# parser for forms
class Parser < Parslet::Parser
  rule(:spaces) do
    match('\s').repeat(1)
  end

  rule(:spaces?) do
    spaces.maybe
  end

  root :form
end

# variable
class Parser < Parslet::Parser
  rule(:variable) do
    match('\w+').repeat(1).as(:variable)
  end

  rule(:variable_assignment) do
    variable >> str(':') >> spaces?
  end
end

# type
class Parser < Parslet::Parser
  rule(:type) do
    Type.descendants.map { |type| str(type.type) }.reduce(&:|).as(:type) >> spaces?
  end
end

# statement
class Parser < Parslet::Parser
  rule(:assignment?) do
    (str('=') >> spaces? >> expression).maybe >> spaces?
  end

  rule(:question) do
    (string_literal >> variable_assignment >> type >> assignment?).as(:question) >> spaces?
  end

  rule(:block) do
    str('{') >> spaces? >> (question | if_statement).repeat.as(:block) >> str('}') >> spaces?
  end

  rule(:if_statement) do
    (str('if') >> spaces? >> expression >> block).as(:if_statement)
  end
end

# literal
class Parser < Parslet::Parser
  rule(:boolean_literal) do
    (str('true') | str('false')).as(:boolean) >> spaces?
  end

  rule(:integer_literal) do
    match('[0-9]').repeat(1).as(:integer) >> spaces?
  end

  rule(:string_literal) do
    str('"') >> match('[^"]').repeat.as(:string) >> str('"') >> spaces?
  end
end

# form
class Parser < Parslet::Parser
  rule(:form) do
    spaces? >> (str('form') >> spaces? >> variable >> spaces? >> block).as(:form)
  end
end

# expression
class Parser < Parslet::Parser
  rule(:integer_negation?) do
    str('-').as(:integer_negation).maybe
  end

  rule(:boolean_negation?) do
    str('!').as(:boolean_negation).maybe
  end

  rule(:negation?) do
    (str('!') | str('-')).as(:negation).maybe
  end

  rule(:variable_or_literal) do
    (boolean_negation? >> boolean_literal | integer_negation? >> integer_literal | string_literal | negation? >> variable) >> spaces?
  end

  rule(:calculation) do
    variable_or_literal.as(:left) >> operator >> expression.as(:right)
  end

  # TODO fix this
  rule(:operator) do
    descendants = BooleanExpression.descendants +  ArithmeticExpression.descendants + ComparisonEqual.descendants + ComparisonOrdering.descendants
    descendants.map { |binary_expression| str(binary_expression.to_operator) }.reduce(&:|).as(:operator) >> spaces?
  end


  rule(:expression) do
    str('(') >> spaces? >> expression.as(:expression) >> spaces? >> str(')') >> spaces? | calculation | variable_or_literal
  end
end