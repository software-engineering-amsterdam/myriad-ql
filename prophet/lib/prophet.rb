require 'parslet'
require 'tk'

require 'gem_ext/tk'
require 'prophet/ast'
require 'prophet/checkers'
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
    puts '--> Reading file...'
    dsl = File.open(filename).read

    puts '--> Parsing form...'
    parser = Parser.new
    parsed = parser.parse(dsl)

    puts '--> Building AST...'
    transformer = Transform.new
    ast = transformer.apply(parsed)

    puts '--> Checking syntax...'
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
      puts
      puts "Detected #{errors.count} errors:"
      puts errors
    else
      puts '--> Launching GUI...'
      gui = Gui.new(ast)
      gui.render

      puts 'Bye bye!'
    end
  end
end
