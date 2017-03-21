module Prophet
  class Evaluator
    attr_reader :context

    def initialize(ast)
      @ast = ast
      @context = {}
    end

    def evaluate
      ast.select do |node|
        Ast::Question === node && node.value
      end.each do |question|
        context[question.identifier.name.to_s] = question.value.visit(Visitors::ExpressionEvaluator.new(context))
      end
    end

    private

    attr_reader :ast
  end
end
