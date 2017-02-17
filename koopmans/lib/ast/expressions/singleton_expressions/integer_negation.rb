require 'parslet'

class IntegerNegation < SingletonExpression
  def self.to_operator
    '-'
  end
end

class Parslet::Parser
  rule(:integer_negation?) do
    str('-').as(:integer_negation).maybe
  end
end

class Parslet::Transform
  rule(integer_negation: simple(:integer_negation), integer: simple(:integer)) do
    IntegerNegation.new(IntegerLiteral.new(integer))
  end
end