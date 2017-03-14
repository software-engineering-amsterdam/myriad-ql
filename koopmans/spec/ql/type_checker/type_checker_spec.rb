require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QL
  module TypeChecker
    include AST

    describe TypeChecker do
      let(:notification_messages) {
        TypeChecker.new.check(generate_form)
        NotificationTable.index.map(&:message)
      }

      describe 'duplicate questions' do
        it 'should detect duplicate label' do
          expect(notification_messages).to include('question with label \'Did you sell a house in 2010?\' is defined multiple times')
        end
        it 'should detect duplicate variable' do
          expect(notification_messages).to include('variable \'hasSoldHouse\' is defined multiple times')
        end
      end

      describe 'undefined variable' do
        it 'should detect undefined variable' do
          expect(notification_messages).to include('variable \'_\' is undefined')
        end
      end

      describe 'invalid operands' do
        it 'should detect undefined variable' do
          expect(notification_messages.to_s).to include('incompatible types at')
        end

        it 'should detect non boolean at if condition' do
          expect(notification_messages.to_s).to include('is not of the type boolean')
        end
      end

      describe 'cyclic dependency' do
        it 'should detect a cyclic dependency' do
          expect(notification_messages).to include('question \'privateDebt\' has a cyclic dependency')
          expect(notification_messages).to include('question \'sellingPrice\' has a cyclic dependency')
        end
      end

      # example ast for form with errors
      def generate_form
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
        cyclic_question             = ComputedQuestion.new(StringLiteral.new('Private debts for the sold house:'), cyclic_question_variable, IntegerType.new, Expression.new([IntegerLiteral.new('5'), And.new(cyclic_question_variable)]))
        cyclic_question2            = ComputedQuestion.new(StringLiteral.new('What was the selling price?'), cyclic_question_variable2, IntegerType.new, cyclic_question_variable2)

        # create if statement with a non boolean condition
        if_statement                = IfStatement.new(undefined_question_variable, [question])

        # create the ast with all the errors
        Form.new('_', [question, question, undefined_question, if_statement, computed_question, cyclic_question, cyclic_question2])
      end
    end
  end
end
