require 'parslet'

class BooleanNegation < SingletonExpression
  def self.to_operator
    '!'
  end
end

class Parslet::Parser
  rule(:boolean_negation?) do
    str('!').as(:boolean_negation).maybe
  end
end

class Parslet::Transform
  rule(boolean_negation: simple(:boolean_negation), boolean: simple(:boolean)) do
    BooleanNegation.new(BooleanLiteral.new(boolean))
  end
end