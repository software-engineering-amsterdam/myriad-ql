module QL
  module TypeChecker
    class TypeChecker
      include Visitor

      def self.check(ast)
        { errors:   [DuplicateVariableChecker, UndefinedVariableChecker,
                     OperandsTypeChecker, CyclicChecker].map { |checker| ast.accept(checker.new) }.flatten,
          warnings: ast.accept(DuplicateLabelChecker.new) }
      end
    end
  end
end