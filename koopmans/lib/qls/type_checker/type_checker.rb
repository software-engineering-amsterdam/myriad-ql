module QLS
  module TypeChecker
    class TypeChecker

      def check(qls_ast, ql_ast)
        ql_questions = ql_ast.accept(QL::TypeChecker::QuestionCollector.new).flatten
        ql_variable_names = ql_questions.map(&:variable).map(&:name)

        qls_variable_names = qls_ast.accept(QuestionVariableCollector.new).flatten.compact.map(&:name)

        questions_placed_checker(qls_variable_names, ql_variable_names)

        pp ql_variable_names
        pp qls_variable_names
      end

      def questions_placed_checker(qls_variable_names, ql_variable_names)
        (ql_variable_names - qls_variable_names).map { |error| NotificationTable.store(Notification::Error.new("#{error} of the QL program is not placed by the QLS program.")) }
      end

    end
  end
end