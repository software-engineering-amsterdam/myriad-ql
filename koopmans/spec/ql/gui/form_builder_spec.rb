require 'rspec'
require 'require_all'
require 'pp'

require_all 'lib'

module QL
  module GUI
    include AST

    describe FormBuilder do
      # let(:form_builder) { FormBuilder.new }
      # let(:gui) { Gui.new }

      # FormBuilder.new(ql_ast, self)

      context 'form with one question' do
        it 'parses' do
          FormBuilder.new(form_with_one_boolean_question_ast, GUI.new)
        end
      end

      def form_with_one_boolean_question_ast
        question_variable           = Variable.new('hasSoldHouse')
        question                    = Question.new('Did you sell a house in 2010?', question_variable, BooleanType)
        Form.new('_', [question])
      end

      def form_ast
        # arithmetic expression
        arithmetic_expression = Expression.new([IntegerLiteral.new(5), Add.new(IntegerLiteral.new(10))])

        # boolean expression
        boolean_expression= Expression.new([BooleanLiteral.new(true), And.new(BooleanLiteral.new(false))])

        # create question
        question_variable           = Variable.new('hasSoldHouse')
        question                    = Question.new('Did you sell a house in 2010?', question_variable, BooleanType)

        # create computed question
        # computed_question_variable  = Variable.new('valueResidue')
        # computed_question           = Question.new('Value residue:', computed_question_variable, IntegerType, Expression.new(Add.new(IntegerLiteral.new(1), IntegerLiteral.new(2))))


        Form.new('_', [question])
        # create cyclic dependency
        # cyclic_question_variable    = Variable.new('privateDebt')
        # cyclic_question_variable2   = Variable.new('sellingPrice')
        # cyclic_question             = Question.new('Private debts for the sold house:', cyclic_question_variable, IntegerType, Subtract.new(cyclic_question_variable, undefined_question_variable))
        # cyclic_question2            = Question.new('What was the selling price?', cyclic_question_variable2, IntegerType, Subtract.new(cyclic_question_variable2, undefined_question_variable))

        # create if statement with a non boolean condition
        # if_statement                = IfStatement.new(undefined_question_variable, [question])

        # create the ast with all the errors
        # Form.new('_', [question, question, undefined_question, if_statement, computed_question, cyclic_question, cyclic_question2])
      end
    end
  end
end