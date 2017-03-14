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

      def eval
        VariableTable.find(name)
      end

      def eval_type
        VariableTable.find(name)
      end
    end
  end
end