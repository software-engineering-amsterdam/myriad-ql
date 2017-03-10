require 'active_support/inflector'

module Ast
  module Evaluatable
    def eval
      raise NotImplementedError
    end
  end

  class Node < Struct
    prepend Enumerable

    def visit(visitor, parent = nil)
      visitor.send(method_name, self, parent)
      children.each { |child| child.visit(visitor, self) }
    end

    def each(&block)
      block.call(self)
      children.each { |child| child.each(&block) }
    end
    alias walk each

    def children
      values.each_with_object([]) do |child, memo|
        if child.kind_of? Node
          memo << child
        elsif child.kind_of? Array
          child.each { |c| memo << c if c.kind_of? Node }
        end
      end
    end

    def node_name
      ActiveSupport::Inflector.underscore ActiveSupport::Inflector.demodulize(self.class.name)
    end

    def method_name
      "visit_#{node_name}"
    end
  end

  class Form < Node.new(:identifier, :body)
  end

  class Question < Node.new(:string, :type, :identifier, :value)
  end

  class IfStatement < Node.new(:condition, :if_true, :if_false)
  end

  class Identifier < Node.new(:name)
  end

  class Literal < Node.new(:value)
  end

  class TextLiteral < Literal
  end

  class NumberLiteral < Literal
  end

  class BoolLiteral < Literal
  end

  class Type < Node.new(:name)
  end

  class Expression < Node
  end

  class UnaryExpression < Expression.new(:value)
  end

  class BinaryExpression < Expression.new(:left, :right)
  end

  class Multiplication < BinaryExpression
  end

  class Division < BinaryExpression
  end

  class Addition < BinaryExpression
  end

  class Subtraction < BinaryExpression
  end
end
