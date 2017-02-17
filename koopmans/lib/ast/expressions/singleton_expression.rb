require 'parslet'

class SingletonExpression < Expression
  attr_reader :expression

  def initialize(expression)
    @expression = expression
  end
end

class Parslet::Parser
  rule(:negation?) do
    (str('!') | str('-')).as(:negation).maybe
  end
end

class Parslet::Transform
  SingletonExpression.descendants.each do |singleton_expression|
    rule(negation: singleton_expression.to_operator, variable: simple(:variable)) do
      singleton_expression.new(Variable.new(variable))
    end
  end
end