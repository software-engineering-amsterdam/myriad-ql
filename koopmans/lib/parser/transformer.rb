require_relative '../ast/statement'
require_relative '../ast/form'
require_relative '../ast/expression'
require_relative '../ast/type'
require_relative '../ast/literal'
require 'parslet'

# defines rules to match parts of a captured tree and transform them into abstract syntax tree
class Transformer < Parslet::Transform

  # form
  rule(form: {variable: simple(:variable), block: subtree(:block)}) do
    Form.new(Variable.new(variable), block)
  end


  # questions
  def self.types(types)
    types.each do |name, class_name|
      rule(question: {string: simple(:string), variable: simple(:variable), type: name.to_s}) do
        Question.new(string, Variable.new(variable), class_name.new)
      end
      rule(question: {string: simple(:string), variable: simple(:variable), type: name.to_s, expression: subtree(:expression)}) do
        Question.new(string, Variable.new(variable), class_name.new, expression)
      end
    end
  end

  types boolean: BooleanType,
        integer: IntegerType,
        date: DateType,
        decimal: DecimalType,
        string: StringType,
        money: MoneyType

  # if statement
  rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) do
    IfStatement.new(expression, block)
  end


  # variable
  rule(variable: simple(:variable)) do
    Variable.new(variable)
  end

  # negative variable
  def self.negation_types(negation_types)
    negation_types.each do |name, class_name|
      rule(negation: name.to_s, variable: simple(:variable)) do
        class_name.new(Variable.new(variable))
      end
    end
  end

  negation_types '!': BooleanNegation,
                 '-': IntegerNegation

  # arithmetic
  def self.arithmetics(arithmetics)
    arithmetics.each do |name, class_name|
      rule({left: subtree(:left), arithmetic: name.to_s, right: subtree(:right)}) do
        class_name.new(left, right)
      end
    end
  end

  arithmetics '-': Subtract,
              '+': Add,
              '*': Multiply,
              '/': Divide

  # boolean
  def self.booleans(booleans)
    booleans.each do |name, class_name|
      rule({left: subtree(:left), boolean: name.to_s, right: subtree(:right)}) do
        class_name.new(left, right)
      end
    end
  end

  booleans '||': Or,
           '&&': And

  # comparison
  def self.comparisons(comparisons)
    comparisons.each do |name, class_name|
      rule({left: subtree(:left), comparison: name.to_s, right: subtree(:right)}) do
        class_name.new(left, right)
      end
    end
  end

  comparisons '<': Less,
              '>': Greater,
              '<=': LessEqual,
              '>=': GreaterEqual,
              '==': Equal,
              '!=': NotEqual


  # boolean literal
  rule(boolean: simple(:boolean)) do
    BooleanLiteral.new(boolean)
  end

  # negative boolean literal
  rule(boolean_negation: simple(:boolean_negation), boolean: simple(:boolean)) do
    BooleanNegation.new(BooleanLiteral.new(boolean))
  end

  # integer literal
  rule(integer: simple(:integer)) do
    IntegerLiteral.new(integer)
  end

  # negative integer literal
  rule(integer_negation: simple(:integer_negation), integer: simple(:integer)) do
    IntegerNegation.new(IntegerLiteral.new(integer))
  end

  # string literal
  rule(string: simple(:string)) do
    StringLiteral.new(string)
  end

end
