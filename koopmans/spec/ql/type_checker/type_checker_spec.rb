require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QL
  module TypeChecker
    include AST

    describe TypeChecker do
      it 'detects duplicate label and variable' do
        question_variable           = Variable.new('hasSoldHouse')
        question                    = Question.new(StringLiteral.new('Did you sell a house in 2010?'), question_variable, BooleanType.new)

        TypeChecker.new.check(Form.new('_', [question, question]))
        messages = NotificationTable.index.map(&:message)
        expect(messages).to include('question with label \'Did you sell a house in 2010?\' is defined multiple times')
        expect(messages).to include('variable \'hasSoldHouse\' is defined multiple times')
      end


      #
      # describe DuplicateVariableChecker do
      #   it 'detects error' do
      #     expect(form_ast.accept(DuplicateVariableChecker.new).map(&:message))
      #       .to include('variable \'hasSoldHouse\' is defined multiple times')
      #   end
      # end
      #
      # describe ExpressionVariableCollector do
      #   it 'detects error' do
      #     expect(form_ast.accept(ExpressionVariableCollector.new).map(&:message))
      #       .to include('variable \'_\' is undefined')
      #   end
      # end
      #
      # describe Evaluator do
      #   it 'detects error' do
      #     messages = form_ast.accept(Evaluator.new).map(&:message)
      #     expect(messages).to include('QL::AST::BooleanType can not be used with QL::AST::Add')
      #     expect(messages).to include('QL::AST::IntegerType can not be used with QL::AST::BooleanType')
      #   end
      # end
      #
      # describe CyclicDependencyChecker do
      #   it 'detects error' do
      #     messages = form_ast.accept(CyclicDependencyChecker.new).map(&:message)
      #     expect(messages).to include('question with variable \'privateDebt\' has a cyclic dependency')
      #     expect(messages).to include('question with variable \'sellingPrice\' has a cyclic dependency')
      #   end
      # end
      #
      # example ast for form with errors
      def form_ast
        # create question
        question_variable           = Variable.new('hasSoldHouse')
        question                    = Question.new(StringLiteral.new('Did you sell a house in 2010?'), question_variable, BooleanType.new)

        # create undefined computed question
        undefined_variable          = Variable.new('_')
        undefined_question_variable = Variable.new('hasBoughtHouse')
        undefined_question          = ComputedQuestion.new(StringLiteral.new('Did you sell a house in 2010?'), undefined_question_variable, IntegerType.new, undefined_variable)

        # create computed question
        computed_question_variable  = Variable.new('valueResidue')
        computed_question           = ComputedQuestion.new(StringLiteral.new('Value residue:'), computed_question_variable, IntegerType.new, undefined_question_variable)

        # create cyclic dependency
        cyclic_question_variable    = Variable.new('privateDebt')
        cyclic_question_variable2   = Variable.new('sellingPrice')
        cyclic_question             = ComputedQuestion.new(StringLiteral.new('Private debts for the sold house:'), cyclic_question_variable, IntegerType.new, cyclic_question_variable)
        cyclic_question2            = ComputedQuestion.new(StringLiteral.new('What was the selling price?'), cyclic_question_variable2, IntegerType.new, cyclic_question_variable2)

        # create if statement with a non boolean condition
        if_statement                = IfStatement.new(undefined_question_variable, [question])

        # create the ast with all the errors
        Form.new('_', [question, question, undefined_question, if_statement, computed_question, cyclic_question, cyclic_question2])
      end
    end
  end
end
