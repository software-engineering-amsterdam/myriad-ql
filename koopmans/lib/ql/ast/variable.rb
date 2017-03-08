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
        QuestionTable.find(name)
        # IntegerLiteral.new('1')
      end
    end
  end
end