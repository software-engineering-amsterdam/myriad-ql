class MainChecker
  require_relative 'duplicate_label_checker'
  require_relative 'duplicate_variable_checker'
  require_relative 'variable_visitor'
  require_relative 'undefined_variable_checker'
  require_relative 'operands_type_checker'
  require_relative 'cyclic_checker'

  def check(ast)
    errors = []
    [DuplicateLabelChecker, DuplicateVariableChecker, UndefinedVariableChecker, OperandsTypeChecker, CyclicChecker].each do |checker|
      errors.push(ast.accept(checker.new))
    end
    errors.flatten
  end
end