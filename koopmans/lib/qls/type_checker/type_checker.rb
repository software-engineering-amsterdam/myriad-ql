module QLS
  module TypeChecker
    class TypeChecker
      def check(qls_ast, ql_ast)
        ql_question_collector = QL::TypeChecker::QuestionCollector.new
        ql_ast.accept(ql_question_collector)
        ql_questions = ql_question_collector.questions
        ql_variable_names = ql_questions.map(&:variable).map(&:name)

        qls_variables_collector = QuestionVariableCollector.new
        qls_ast.accept(qls_variables_collector)
        qls_variable_names = qls_variables_collector.variables.map(&:name)

        questions_placed_checker(qls_variable_names, ql_variable_names)
        questions_reference_checker(qls_variable_names, ql_variable_names)
        questions_uniqueness_checker(qls_variable_names)
        widget_type_checker(qls_ast, ql_questions)
      end

      def questions_placed_checker(qls_variable_names, ql_variable_names)
        (ql_variable_names - qls_variable_names).each do |error|
          NotificationTable.store(Notification::Error.new("#{error} of the QL program is not placed by the QLS program."))
        end
      end

      def questions_reference_checker(qls_variable_names, ql_variable_names)
        (qls_variable_names - ql_variable_names).each do |error|
          NotificationTable.store(Notification::Error.new("#{error} is referenced to a question that is not in the QL program"))
        end
      end

      def questions_uniqueness_checker(qls_variable_names)
        select_duplicates(qls_variable_names).each do
          |error| NotificationTable.store(Notification::Error.new("#{error} is placed multiple times"))
        end
      end

      def widget_type_checker(qls_ast, ql_questions)
        ql_variable_type_map = {}
        ql_questions.each do |ql_question|
          ql_variable_type_map[ql_question.variable.name] = ql_question.type
        end
        qls_ast.accept(WidgetTypeChecker.new(ql_variable_type_map))
      end

      def select_duplicates(elements)
        elements.select { |element| elements.count(element) > 1 }.uniq
      end
    end
  end
end
