class TypeChecker
  require_relative 'duplicate_label_checker'
  require_relative 'duplicate_variable_checker'
  require_relative 'undefined_variable_checker'
  require_relative 'operands_type_checker'
  require_relative 'cyclic_checker'

  def check(ast)
    pp [DuplicateLabelChecker, DuplicateVariableChecker, UndefinedVariableChecker,
        OperandsTypeChecker, CyclicChecker].map { |checker| ast.accept(checker.new) }.flatten
  end
end