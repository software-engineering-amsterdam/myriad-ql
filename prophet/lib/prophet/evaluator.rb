module Prophet
  class Evaluator
    def initialize(ast, state)
      @ast = ast
      @state = state
    end

    def evaluate
      evaluate_questions
      evaluate_conditionals
      state
    end

    private

    attr_reader :ast, :state

    def evaluate_questions
      sorted_questions_with_value.each do |question|
        value = question.value.visit Visitors::ExpressionEvaluator.new(state)
        state[question.identifier.name.to_s] = value
      end
    end

    def evaluate_conditionals
      ast.select_by_type(:if_statement, :if_else_statement).each do |node|
        value = node.condition.visit Visitors::ExpressionEvaluator.new(state)
        state[node] = value
      end
    end

    def sorted_questions_with_value
      sorted_identifier_names = dependency_hash.tsort
      ast.select_by_type(:question_with_value).sort_by do |question|
        sorted_identifier_names.index question.identifier.name.to_s
      end
    end

    def dependency_hash
      @dependency_hash ||= begin
        mapping = ast.select_by_type(:question_with_value).map do |question|
          [question.identifier.name.to_s,
           question.value.select_by_type(:identifier).map(&:name).map(&:to_s)]
        end
        DependencyHash[mapping]
      end
    end
  end
end
