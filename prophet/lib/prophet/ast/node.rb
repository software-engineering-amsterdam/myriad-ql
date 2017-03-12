require 'active_support/inflector'

module Prophet
  module Ast
    class Node < Struct
      # We want to use Enumerable's implementation of select, ...
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
  end
end
