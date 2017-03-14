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

      context 'ast with one question' do
        it 'should have only one question frame' do
          form = Form.new('_', [question])
          expect(form.accept(form_builder).first).to be_a(QuestionFrame)
          expect(form.accept(form_builder).size).to be(1)
        end
      end

      context 'ast with one computed question' do
        it 'should have only one computed question frame' do
          form = Form.new('_', [computed_question])
          expect(form.accept(form_builder).first).to be_a(ComputedQuestionFrame)
          expect(form.accept(form_builder).size).to be(1)
        end
      end

      context 'ast with if statement and one question' do
        it 'should have only one question frame with a condition' do
          form = Form.new('_', [if_statement])
          expect(form.accept(form_builder).first.condition).not_to be(nil)
        end
      end

      context 'ast with nested if statements and one question' do
        it 'should have only one question frame with both conditions' do
          outer_if_statement = IfStatement.new(condition, [if_statement])
          form = Form.new('_', [outer_if_statement])
          expect(form.accept(form_builder).first.condition.expression).to include(And)
        end
      end
    end
  end
end