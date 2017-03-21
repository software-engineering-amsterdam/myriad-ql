module QLS
  module TypeChecker
    class TypeChecker

      def check(qls_ast, ql_ast)
        ql_questions = ql_ast.accept(QL::TypeChecker::QuestionCollector.new).flatten
        ql_variables = ql_questions.map(&:variable).map(&:name)

        qls_variables = qls_ast.accept(VariableCollector.new)
        pp qls_variables



        # { errors: [QuestionChecker, WidgetChecker].map { |checker| stylesheet.accept(checker.new, form) }.flatten }
      end
    end
  end
end