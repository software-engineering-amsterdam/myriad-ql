module QL
  module TypeChecker
    class TypeChecker
      def check(ast)
        questions = ast.accept(QuestionCollector.new).flatten
        duplicate_label_checker(questions)
        duplicate_variable_checker(questions)
        undefined_variable_checker(questions, ast)
        operands_type_checker(questions, ast)
        cyclic_dependency_checker(questions)
      end

      def duplicate_label_checker(questions)
        duplicate_labels = select_duplicates(questions.map(&:label).map(&:value))
        duplicate_labels.each do |label|
          NotificationTable.store(Notification::Warning.new("question with label '#{label}' is defined multiple times"))
        end
      end

      def duplicate_variable_checker(questions)
        duplicate_variables = select_duplicates(questions.map(&:variable).map(&:name)).uniq
        duplicate_variables.each do |variable|
          NotificationTable.store(Notification::Error.new("variable '#{variable}' is defined multiple times"))
        end
      end

      def undefined_variable_checker(questions, ast)
        question_variables = questions.map(&:variable).map(&:name)
        expression_variables = ast.accept(ExpressionVariableCollector.new).flatten.compact.map(&:name)

        (expression_variables - question_variables).each do |undefined_variable|
          NotificationTable.store(Notification::Error.new("variable '#{undefined_variable}' is undefined"))
        end
      end

      def operands_type_checker(questions, ast)
        variable_type_map = {}
        questions.each do |question|
          variable_type_map[question.variable.name] = question.type
        end
        ast.accept(OperandsTypeEvaluator.new, variable_type_map)
      end

      def cyclic_dependency_checker(questions)
        computed_questions = questions.select { |q| q.is_a?(AST::ComputedQuestion) }

        @variable_dependencies_map = {}
        build_variable_dependencies_map(computed_questions)


        @variable_dependencies_map.each do |variable_name, dependencies|
          if dependencies
            dependencies.each do |dependency|
              check_dependency(variable_name, dependency)
            end
          end
        end
      end

      def build_variable_dependencies_map(computed_questions)
        computed_questions.each do |computed_question|
          assignment_variables = computed_question.accept(ExpressionVariableCollector.new).flatten.compact
          @variable_dependencies_map[computed_question.variable.name] = assignment_variables
        end
      end

      # add new dependency to original dependency hash if they exist, don't add duplicates
      def check_dependency(variable_name, dependency)
        next_dependencies = @variable_dependencies_map[dependency.name]
        if next_dependencies
          @variable_dependencies_map[variable_name] |= next_dependencies
          is_cyclic_dependency?(variable_name, dependency)
        end
      end

      # check for cyclic dependency if there is a dependency on itself, else visit the next variable
      def is_cyclic_dependency?(variable_name, dependency)
        if @variable_dependencies_map[variable_name].map(&:name).include?(variable_name)
          NotificationTable.store(Notification::Error.new("question '#{variable_name}' has a cyclic dependency"))
        end
      end

      def select_duplicates(elements)
        elements.select { |element| elements.count(element) > 1 }.uniq
      end
    end
  end
end