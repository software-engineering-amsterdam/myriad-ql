class MainChecker
  require_relative 'duplicate_label_checker'
  require_relative 'duplicate_variable_checker'
  require_relative 'variable_visitor'
  require_relative 'undefined_variable_checker'
  require_relative 'condition_type_checker'
  require_relative 'operands_type_checker'
  require_relative 'cyclic_checker'

  def check(ast)
    errors = []
    [DuplicateLabelChecker, DuplicateVariableChecker, UndefinedVariableChecker, ConditionTypeChecker, OperandsTypeChecker,
     CyclicChecker].each do |checker|
      errors.push(ast.accept(checker.new))
    end
    errors.flatten
  end

  #
  #
  # duplicate_labels = ast.accept(DuplicateLabelChecker.new)
  # if duplicate_labels
  #   p '[WARNING] duplicate labels:'
  #   p duplicate_labels
  #   p ""
  # end
  #
  # duplicate_question_variables = ast.accept(DuplicateVariableChecker.new)
  # if duplicate_question_variables
  #   p '[ERROR] duplicate question declarations with different types:'
  #   p duplicate_question_variables
  #   p ""
  # end
  #
  # undefinedVariables = ast.accept(UndefinedVariableChecker.new)
  # if undefinedVariables
  #   p '[ERROR] reference to undefined questions:'
  #   p undefinedVariables
  #   p ""
  # end
  #
  # conditions = ast.accept(ConditionTypeChecker.new)
  # if conditions
  #   p '[ERROR] conditions that are not of the type boolean:'
  #   p conditions
  #   p ""
  # end
  #
  # operands = ast.accept(OperandsTypeChecker.new)
  # if operands
  #   p 'operands of invalid type to operators:'
  #   operands.each do |o|
  #     p o
  #   end
  #   p ""
  # end
  #
  # cyclic = ast.accept(CyclicChecker.new)
  # if cyclic
  #   p 'cyclic dependencies between questions:'
  #   cyclic.each do |o|
  #     p o
  #   end
  #   p ""
  # end
end