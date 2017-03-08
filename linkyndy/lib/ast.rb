require 'active_support/inflector'

module Ast
  module Evaluatable
    def eval
      raise NotImplementedError
    end
  end

  class Form < Struct.new(:identifier, :body)
    include Visitable
  class Node < Struct
    def visit(visitor, parent = nil)
      visitor.send(method_name, self, parent)
      children.each { |child| child.visit(visitor, self) }
    end

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

  class Question < Struct.new(:string, :type, :identifier, :value)
    include Visitable
  end

  class IfStatement < Struct.new(:condition, :if_true, :if_false)
    include Visitable
  end

  class Identifier < Struct.new(:name)
  end

  class String < Struct.new(:value)
  end

  class Integer < Struct.new(:value)
  end

  class Boolean < Struct.new(:value)
  end

  class Type < Struct.new(:name)
  end

  class Multiplication < Struct.new(:left, :right)
  end

  class Division < Struct.new(:left, :right)
  end

  class Addition < Struct.new(:left, :right)
  end

  class Subtraction < Struct.new(:left, :right)
  end
end
