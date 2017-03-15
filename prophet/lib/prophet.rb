require 'parslet'

require 'prophet/ast'
require 'prophet/checkers'
require 'prophet/collectors'
require 'prophet/evaluator'
require 'prophet/parser'
require 'prophet/transform'
require 'prophet/utils/dependency_hash'
require 'prophet/version'

module Prophet
  def self.interpret(filename)
    dsl = File.open(filename).read

    parser = Parser.new
    parsed = parser.parse(dsl)

    transformer = Transform.new
    ast = transformer.apply(parsed)

    [
      Checkers::UndefinedIdentifiers, Checkers::DuplicateIdentifiers,
      Checkers::InvalidOperands, Checkers::CyclicDependencies
    ].each do |checker|
      checker.new(ast).check
    end

    evaluator = Evaluator.new(ast)
    evaluator.evaluate
  end
end
