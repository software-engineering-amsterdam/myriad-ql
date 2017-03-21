module QLS
  module TypeChecker
    class TypeChecker

      def check(qls_ast, ql_ast)
        ql_questions = ql_ast.accept(QL::TypeChecker::QuestionCollector.new).flatten
        ql_variable_names = ql_questions.map(&:variable).map(&:name)
        qls_variable_names = qls_ast.accept(QuestionVariableCollector.new).flatten.compact.map(&:name)

        questions_placed_checker(qls_variable_names, ql_variable_names)
        questions_reference_checker(qls_variable_names, ql_variable_names)
        questions_uniqueness_checker(qls_variable_names)
        widget_type_checker(qls_ast, ql_questions)
      end

      def questions_placed_checker(qls_variable_names, ql_variable_names)
        (ql_variable_names - qls_variable_names).map { |error| NotificationTable.store(Notification::Error.new("#{error} of the QL program is not placed by the QLS program.")) }
      end

      def questions_reference_checker(qls_variable_names, ql_variable_names)
        (qls_variable_names - ql_variable_names).map { |error| NotificationTable.store(Notification::Error.new("#{error} is referenced to a question that is not in the QL program")) }
      end

      def questions_uniqueness_checker(qls_variable_names)
        select_duplicates(qls_variable_names).map { |error| NotificationTable.store(Notification::Error.new("#{error} is placed multiple times")) }
      end

      def widget_type_checker(qls_ast, ql_questions)
        ql_variable_type_map = {}
        ql_questions.each do |ql_question|
          ql_variable_type_map[ql_question.variable.name] = ql_question.type
        end
        qls_ast.accept(TypeWidgetMapCollector.new, ql_variable_type_map).flatten.compact

        # invalid_widget_types?(qls_widgets)
      end

      # def invalid_widget_types?(qls_widgets)
      #   qls_widgets.each do |type_widget_map|
      #     type_widget_map.each do |type, widget|
      #       widget.first.accept(WidgetTypeChecker.new, type)
      #     end
      #   end
      # end

      def select_duplicates(elements)
        elements.select { |element| elements.count(element) > 1 }.uniq
      end
    end
  end
end
