require 'active_support/inflector'

module Prophet
  module Ast
    class Node < Struct
      # We want to use Enumerable's implementation of select, ...
      prepend Enumerable

      def visit(visitor)
        visitor.send(method_name, self)
      end

      def each(&block)
        block.call(self)
        children.each { |child| child.each(&block) }
      end
      alias walk each

      def select_by_type(*types)
        qualified_types = types.map do |type|
          ActiveSupport::Inflector.constantize("Prophet::Ast::#{ActiveSupport::Inflector.camelize(type)}")
        end
        select { |node| qualified_types.any? { |type| node.is_a? type }}
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

      def line_and_column
        first_value = values.first
        case first_value
        when Parslet::Slice, Node
          first_value.line_and_column
        when Array
          first_value.first.line_and_column
        else
          []
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
