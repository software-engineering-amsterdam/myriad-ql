module Prophet
  class Evaluator
    attr_reader :context

    def initialize(ast)
      @ast = ast
      @context = {}
    end

    def evaluate
      sorted_questions.each do |question|
        context[question.identifier.name.to_s] = question.value.visit(Visitors::ExpressionEvaluator.new(context))
      end
    end

    private

    attr_reader :ast

    def dependency_hash
      @dependency_hash ||= DependencyHash[
        ast.select_by_type(:question_with_value).map do |question|
          [question.identifier.name.to_s,
           question.select_by_type(:identifier).map(&:name).map(&:to_s)]
        end
      ]
    end

    def sorted_questions
      sorted_identifier_names = dependency_hash.tsort
      ast.select_by_type(:question_with_value).sort_by do |question|
        sorted_identifier_names.index question.identifier.name.to_s
      end
    end
  end
end
