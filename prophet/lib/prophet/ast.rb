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

    # def walk(&block)
    #   initial = block_given? && !block.call(self) ? [] : [self]
    #   children.inject(initial) do |memo, child|
    #     (memo << child.walk(&block)).flatten
    #   end
    # end

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
    def eval(context)
      context[name.to_s]
    end
  end

  class Literal < Node.new(:value)
  end

  class TextLiteral < Literal
    def eval(context)
      value.to_s
    end
  end

  class NumberLiteral < Literal
    def eval(context)
      value.to_i
    end
  end

  class BoolLiteral < Literal
    def eval(context)
      value == 'true'
    end
  end

  class Type < Node.new(:name)
  end

  class Expression < Node
    def eval(context)
    end
  end

  class UnaryExpression < Expression.new(:value)
    def eval(context)
      value.eval(context)
    end
  end

  class BinaryExpression < Expression.new(:left, :right)
  end

  class Multiplication < BinaryExpression
    def eval(context)
      left.eval(context) * right.eval(context)
    end
  end

  class Division < BinaryExpression
    def eval(context)
      left.eval(context) / right.eval(context)
    end
  end

  class Addition < BinaryExpression
    def eval(context)
      left.eval(context) + right.eval(context)
    end
  end

  class Subtraction < BinaryExpression
    def eval(context)
      left.eval(context) - right.eval(context)
    end
  end
end
