module QL
  module AST
    class Variable
      attr_reader :name

      def initialize(name)
        @name = name.to_s
      end

      def accept(visitor)
        visitor.visit_variable(self)
      end
    end
  end
end