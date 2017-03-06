require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QL
  module TypeChecker
    include AST

    describe TypeChecker do
      describe DuplicateLabelChecker do
        it 'detects error' do
          expect(form_ast.accept(DuplicateLabelChecker.new).map(&:message))
            .to include('question with label \'Did you sell a house in 2010?\' is defined multiple times')
        end
      end

      describe DuplicateVariableChecker do
        it 'detects error' do
          expect(form_ast.accept(DuplicateVariableChecker.new).map(&:message))
            .to include('variable \'hasSoldHouse\' is defined multiple times')
        end
      end

      describe UndefinedVariableChecker do
        it 'detects error' do
          expect(form_ast.accept(UndefinedVariableChecker.new).map(&:message))
            .to include('variable \'_\' is undefined')
        end
      end

      describe OperandsTypeChecker do
        it 'detects error' do
          messages = form_ast.accept(OperandsTypeChecker.new).map(&:message)
          expect(messages)
            .to include('QL::AST::BooleanType can not be used with QL::AST::Add')
          expect(messages)
            .to include('QL::AST::IntegerType can not be used with QL::AST::BooleanType')
        end
      end

      describe CyclicChecker do
        it 'detects error' do
          messages = form_ast.accept(CyclicChecker.new).map(&:message)
          expect(messages)
            .to include('question with variable \'privateDebt\' has a cyclic dependency')
          expect(messages)
            .to include('question with variable \'sellingPrice\' has a cyclic dependency')
        end
      end

      # example ast for form
      def form_ast
        # create question
        question_variable           = Variable.new('hasSoldHouse')
        question                    = Question.new('Did you sell a house in 2010?', question_variable, BooleanType)

        # create undefined computed question
        undefined_variable          = Variable.new('_')
        undefined_question_variable = Variable.new('hasBoughtHouse')
        undefined_question          = Question.new('Did you sell a house in 2010?', undefined_question_variable, IntegerType, undefined_variable)

        # create computed question
        computed_question_variable  = Variable.new('valueResidue')
        computed_question           = Question.new('Value residue:', computed_question_variable, IntegerType, Add.new(undefined_question_variable, question_variable))

        # create cyclic dependency
        cyclic_question_variable    = Variable.new('privateDebt')
        cyclic_question_variable2   = Variable.new('sellingPrice')
        cyclic_question             = Question.new('Private debts for the sold house:', cyclic_question_variable, IntegerType, Subtract.new(cyclic_question_variable, undefined_question_variable))
        cyclic_question2            = Question.new('What was the selling price?', cyclic_question_variable2, IntegerType, Subtract.new(cyclic_question_variable2, undefined_question_variable))

        # create if statement with a non boolean condition
        if_statement                = IfStatement.new(undefined_question_variable, [question])

        # create the ast with all the errors
        Form.new('_', [question, question, undefined_question, if_statement, computed_question, cyclic_question, cyclic_question2])
      end
    end
  end
end
