module QL
  module TypeChecker
    class TypeChecker
      include Notification
      include AST

      def check(ast)
        questions = ast.accept(QuestionCollector.new).flatten
        duplicate_label_checker(questions)
        duplicate_variable_checker(questions)
        undefined_variable_checker(questions, ast)
        operands_type_checker(questions, ast)
        cyclic_checker(questions, ast)
      end

      # checkers
      def duplicate_label_checker(questions)
        duplicate_labels = select_duplicates(questions.map(&:label).map(&:to_value))
        duplicate_labels.each do |label|
          NotificationTable.store(Warning.new("question with label '#{label}' is defined multiple times"))
        end
      end

      def duplicate_variable_checker(questions)
        duplicate_variables = select_duplicates(questions.map(&:variable).map(&:name)).uniq
        duplicate_variables.each do |variable|
          NotificationTable.store(Error.new("variable '#{variable}' is defined multiple times"))
        end
      end

      def undefined_variable_checker(questions, ast)
        question_variables   = questions.map(&:variable).map(&:name)
        expression_variables = ast.accept(ExpressionVariableCollector.new).flatten.compact.map(&:name)

        (expression_variables - question_variables).each do |undefined_variable|
          NotificationTable.store(Error.new("variable '#{undefined_variable}' is undefined"))
        end
      end

      def operands_type_checker(questions, ast)
        # create hash with variable and type e.g. {"hasSoldHouse"=>#<BooleanType:0x007f959593fb70>,
        #                                          "hasBoughtHouse"=>#<BooleanType:0x007f9594969ac0>}
        variable_types = {}
        questions.each do |question|
          variable_types[question.variable.name] = question.type
        end

        ast.accept(OperandsTypeChecker.new, variable_types)
      end

      def cyclic_checker(questions, ast)
        # get computed question assignment with dependency variables as hash
        # e.g. {"sellingPrice"=>[#<Variable:0x007ff31ca431e0 @name="privateDebt">, #<Variable:0x007ff31ca4ae90 @name="var1">],
        #       "privateDebt"=>[#<Variable:0x007ff31e17eaf8 @name="sellingPrice">, #<Variable:0x007ff31e1868e8 @name="var2">]}
        variable_dependencies = {}
        computed_questions = questions.select { |q| q.is_a?(ComputedQuestion) }
        computed_questions.each do |computed_question|
          assignment_variables = computed_question.assignment.accept(ExpressionVariableCollector.new).flatten.compact
          variable_dependencies[computed_question.variable.name] = assignment_variables
        end
        ast.accept(CyclicDependencyChecker.new, variable_dependencies)
      end

      # helper
      def select_duplicates(elements)
        elements.select { |element| elements.count(element) > 1 }.uniq
      end
    end
  end
end