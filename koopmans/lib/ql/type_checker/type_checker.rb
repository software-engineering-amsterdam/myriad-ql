module QL
  module TypeChecker
    class TypeChecker
      include Visitor
      include Notification
      include QuestionTable
      include NotificationTable

      def check(ast)
        questions = ast.accept(QuestionCollector.new).flatten

        duplicate_label_checker(questions)
        duplicate_variable_checker(questions)
        undefined_variable_checker(questions, ast)
        operands_type_checker(questions, ast)
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
        # question_variables   = questions.map(&:variable).map(&:name)
        # expression_variables = ast.accept(ExpressionVariableCollector.new).flatten.compact.map(&:name)

        # (expression_variables - question_variables).map { |undefined_variable| Error.new("variable '#{undefined_variable}' is undefined") }
      end

      def operands_type_checker(questions, ast)
        # create hash with variable and type e.g. {"hasSoldHouse"=>#<BooleanType:0x007f959593fb70>,
        #                                          "hasBoughtHouse"=>#<BooleanType:0x007f9594969ac0>}
        questions.each do |question|
          QuestionTable.store(question.variable.name, question.type)
        end

        ast.accept(OperandsTypeChecker.new)
      end

      # helper functions
      def select_duplicates(elements)
        elements.select { |element| elements.count(element) > 1 }.uniq
      end
    end
  end
end