require 'rspec'
require 'require_all'
require 'pp'

require_all 'lib'

module QL
  module GUI
    include AST
    describe FormBuilder do
      let(:form_builder) { FormBuilder.new }
      let(:question) { Question.new('Did you sell a house in 2010?', Variable.new('hasSoldHouse'), BooleanType.new) }
      let(:computed_question) { ComputedQuestion.new('Did you sell a house in 2010?', Variable.new('hasSoldHouse'), MoneyType, IntegerLiteral.new(1))}
      let(:condition) { BooleanLiteral.new(true) }
      let(:if_statement) { IfStatement.new(condition, [question])}
      let(:if_else_statement) { IfElseStatement.new(condition, [question], [question])}

      context 'ast with one question' do
        it 'has only one question frame' do
          form = Form.new('_', [question])
          expect(form.accept(form_builder).first).to be_a(QuestionFrame)
          expect(form.accept(form_builder).size).to be(1)
        end
      end

      context 'ast with one computed question' do
        it 'has only one computed question frame' do
          form = Form.new('_', [computed_question])
          expect(form.accept(form_builder).first).to be_a(ComputedQuestionFrame)
          expect(form.accept(form_builder).size).to be(1)
        end
      end

      context 'ast with if statement and one question' do
        it 'assigns correct condition' do
          form = Form.new('_', [if_statement])
          expect(form.accept(form_builder).first.condition).to be_a(BooleanLiteral)
        end
      end

      context 'ast with nested if statements and one question' do
        it 'assigns correct condition' do
          outer_if_statement = IfStatement.new(condition, [if_statement])
          form = Form.new('_', [outer_if_statement])

          # true && true
          expect(form.accept(form_builder).first.condition.expression[0]).to be_a(BooleanLiteral)
          expect(form.accept(form_builder).last.condition.expression[0]).to be_a(BooleanLiteral)
        end
      end

      context 'ast with if else statement and one question each' do
        it 'assigns correct conditions' do
          form = Form.new('_', [if_else_statement])

          # true && false
          expect(form.accept(form_builder).first.condition).to be_a(BooleanLiteral)
          expect(form.accept(form_builder).last.condition).to be_a(BooleanNegation)
        end
      end

      context 'ast with nested if else statements and one question each' do
        it 'assigns correct conditions' do
          outer_if_else_statement = IfElseStatement.new(condition, [if_else_statement], [if_else_statement])
          form = Form.new('_', [outer_if_else_statement])

          # true && true
          expect(form.accept(form_builder)[0].condition.expression[0]).to be_a(BooleanLiteral)
          expect(form.accept(form_builder)[0].condition.expression[1].expression).to be_a(BooleanLiteral)

          # true && false
          expect(form.accept(form_builder)[1].condition.expression[0]).to be_a(BooleanLiteral)
          expect(form.accept(form_builder)[1].condition.expression[1].expression).to be_a(BooleanNegation)

          # false && true
          expect(form.accept(form_builder)[2].condition.expression[0]).to be_a(BooleanNegation)
          expect(form.accept(form_builder)[2].condition.expression[1].expression).to be_a(BooleanLiteral)

          # false && false
          expect(form.accept(form_builder)[3].condition.expression[0]).to be_a(BooleanNegation)
          expect(form.accept(form_builder)[3].condition.expression[1].expression).to be_a(BooleanNegation)
        end
      end
    end
  end
end