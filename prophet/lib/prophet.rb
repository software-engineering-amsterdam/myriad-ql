require 'parslet'
require 'tk'

require 'gem_ext/tk'
require 'prophet/ast'
require 'prophet/checkers'
require 'prophet/collectors'
require 'prophet/evaluator'
require 'prophet/gui'
require 'prophet/parser'
require 'prophet/transform'
require 'prophet/utils/dependency_hash'
require 'prophet/visitors'
require 'prophet/widgets'
require 'prophet/version'

module Prophet
  def self.interpret(filename)
    dsl = File.open(filename).read

    parser = Parser.new
    parsed = parser.parse(dsl)

    transformer = Transform.new
    ast = transformer.apply(parsed)

    errors = [
      Checkers::UndefinedIdentifiers,
      Checkers::DuplicateIdentifiers,
      Checkers::InvalidConditions,
      Checkers::InvalidOperands,
      Checkers::CyclicDependencies
    ].flat_map do |checker|
      checker.new(ast).check
    end

    if errors.any?
      puts errors
    else
      gui = Gui.new(ast)
      gui.render
    end
  end
end
