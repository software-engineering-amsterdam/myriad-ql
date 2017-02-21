require_relative '../visitor/cyclic_visitor'

class CyclicChecker < BaseVisitor
  def visit_form(subject)
    # get question variables with dependency variables as hash
    # e.g. {"sellingPrice"=>[#<Variable:0x007ff31ca431e0 @name="privateDebt">, #<Variable:0x007ff31ca4ae90 @name="var1">],
    #       "privateDebt"=>[#<Variable:0x007ff31e17eaf8 @name="sellingPrice">, #<Variable:0x007ff31e1868e8 @name="var2">]}
    @values = subject.accept(CyclicVisitor.new).compact.inject(:merge)
    @errors = []

    # do the actual cyclic checking
    subject.statements.map{|u| visit_statement(u)}
    @errors.uniq
  end

  # visit all statements of the if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # visit question, and visit calculation for the assignment of the question
  def visit_question(subject)
    {subject.variable.name => visit_calculation(subject.assignment).flatten.compact} if subject.assignment
  end

  # visit the calculations of both the left and right sides
  def visit_expression(subject)
    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end

  # check if the visited variable is in the dependency hash
  # for each of the dependencies, check their dependencies
  # check if the variable from the dependency is in the dependency hash
  # add new dependency to original dependency hash, don't add duplicates
  # check for cyclic dependency if there is a dependency on itself, else visit the next variable
  def visit_variable(subject)
    if @values.key? subject.name
      @values[subject.name].each do |k|
        if @values.key? k.name
          @values[subject.name] = @values[subject.name] | @values[k.name]
          if @values[subject.name].map(&:name).include? subject.name
            @errors.push("[ERROR]: question with variable '#{subject.name}' has a cyclic dependency")
          else
            visit_variable(k)
          end
        end
      end
    end
    []
  end
end