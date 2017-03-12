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
      context[question.identifier.name.to_s] = question.value.eval(context)
    end
  end

  private

  attr_reader :ast
end
