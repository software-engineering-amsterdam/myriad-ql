require_relative 'base_checker'
require_relative 'cyclic_visitor'

class CyclicChecker < BaseChecker
  def visit_form(subject)
    # get question variables with associated assignment variables as hash
    @values = subject.accept(CyclicVisitor.new)
    @errors = []
    # @values_as_name = @values.keys
    # p @values
    subject.statements.map{|u| visit_statement(u)}
    @errors.uniq
    # p values
    # values.each do |key,value|
    #   visited = []
    #   value.each do |v|
    #     # p values["privateDebt"]
    #     p v
    #     p values[v]
    #   end
    #   dependencies.values[0].each do |dependency|
    #     p dependency
    #   end
    # end
    # nil
  end

  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  def visit_question(subject)
    # {subject.variable.name => visit_calculation(subject.assignment).flatten.compact} if subject.assignment
    {subject.variable.name => visit_calculation(subject.assignment).flatten.compact} if subject.assignment
  end

  def visit_expression(subject)
    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end

  def visit_variable(subject)
    # p @values.keys
    # p @values
    # p 'kijken vanuit:  %s' % subject.name
    test = []
    if @values.key? subject.name
      # p 'jaa'

      # p @values[subject.name]
      @values[subject.name].each do |k|
        if @values.key? k.name
          # p @values[k.name]
          @values[subject.name] = @values[subject.name] | @values[k.name]

          # p @values
          # p 'te veel?'
          # p @values[subject.name]
          if @values[subject.name].map(&:name).include? subject.name
            @errors.push('[ERROR] cyclic at %s' % subject.name)
            # p @errors
          else
            visit_variable(k)
            # p 'NOT YET DETECTED'
          end

          # p @values.select{ |e| @values.count(e) > 1 }
          # pp @values
          # p 'hij zit in het lijstje'
          # if @values[k.name].map(&:name).include? subject.name
          #   p 'cyclic'
          # p @values[subject.name].map(&:name)
          #   if @values[subject.name].map(&:name).include? k.name
          #     p subject.name
          #     p 'DETECTED!!!'
          #     p @values
          #   else
              # p 'NOT YET.......'
          #     @values[subject.name].push(k)
          #   end
            # p
          #
          #   p 'cyclic2'
          # else
          #   test.push(visit_variable(k))
          # end
        else
          # p 'niet in het lijstje'
        end
        # if test.include? k
        #
        # else
        #   test.push(visit_variable(k))
        # end

      end

    end

    # test
    #   p 'true'
    #   test = []
    #   @values[subject.name].each do |v|
    #     if @values[subject.name].include? subject.name
    #       p 'error'
    #     end
    #     test.push(v)
    #   end
    #   test
    #   # test.push(visit_variable())

  end
end