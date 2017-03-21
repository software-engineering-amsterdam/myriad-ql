require 'rspec'
require 'require_all'
require 'pp'

require_all 'lib'

module QL
  module GUI
    include AST
    describe FormBuilder do
      let(:form_builder) { FormBuilder.new }
      let(:question) { Question.new(StringLiteral.new('Did you sell a house in 2010?'), Variable.new('hasSoldHouse'), BooleanType.new) }
      let(:computed_question) { ComputedQuestion.new(StringLiteral.new('Did you sell a house in 2010?'), Variable.new('hasSoldHouse'), MoneyType.new, IntegerLiteral.new(1)) }
      let(:condition) { BooleanLiteral.new(true) }
      let(:if_statement) { IfStatement.new(condition, [question]) }
      let(:if_else_statement) { IfElseStatement.new(condition, [question], [question]) }

      context 'ast with one question' do
        it 'has only one question frame' do
          form = Form.new('_', [question])
          form.accept(form_builder)
          question_frames = form_builder.question_frames
          expect(question_frames.first).to be_a(QuestionFrame)
          expect(question_frames.size).to be(1)
        end
      end

      context 'ast with one computed question' do
        it 'has only one computed question frame' do
          form = Form.new('_', [computed_question])
          form.accept(form_builder)
          question_frames = form_builder.question_frames
          expect(question_frames.first).to be_a(ComputedQuestionFrame)
          expect(question_frames.size).to be(1)
        end
      end

      context 'ast with if statement and one question' do
        it 'assigns correct condition' do
          form = Form.new('_', [if_statement])
          form.accept(form_builder)
          question_frames = form_builder.question_frames
          expect(question_frames.first.condition).to be_a(BooleanLiteral)
        end
      end

      context 'ast with nested if statements and one question' do
        it 'assigns correct condition' do
          outer_if_statement = IfStatement.new(condition, [if_statement])
          form = Form.new('_', [outer_if_statement])
          form.accept(form_builder)
          question_frames = form_builder.question_frames
          # true && true
          expect(question_frames.first.condition.expressions[0]).to be_a(BooleanLiteral)
          expect(question_frames.last.condition.expressions[0]).to be_a(BooleanLiteral)
        end
      end

      context 'ast with if else statement and one question each' do
        it 'assigns correct conditions' do
          form = Form.new('_', [if_else_statement])
          form.accept(form_builder)
          question_frames = form_builder.question_frames
          # true && false
          expect(question_frames.first.condition).to be_a(BooleanLiteral)
          expect(question_frames.last.condition).to be_a(BooleanNegation)
        end
      end

      context 'ast with nested if else statements and one question each' do
        it 'assigns correct conditions' do
          outer_if_else_statement = IfElseStatement.new(condition, [if_else_statement], [if_else_statement])
          form = Form.new('_', [outer_if_else_statement])
          form.accept(form_builder)
          question_frames = form_builder.question_frames

          # true && true
          expect(question_frames[0].condition.expressions[0]).to be_a(BooleanLiteral)
          expect(question_frames[0].condition.expressions[1].expression).to be_a(BooleanLiteral)

          # true && false
          expect(question_frames[1].condition.expressions[0]).to be_a(BooleanLiteral)
          expect(question_frames[1].condition.expressions[1].expression).to be_a(BooleanNegation)

          # false && true
          expect(question_frames[2].condition.expressions[0]).to be_a(BooleanNegation)
          expect(question_frames[2].condition.expressions[1].expression).to be_a(BooleanLiteral)

          # false && false
          expect(question_frames[3].condition.expressions[0]).to be_a(BooleanNegation)
          expect(question_frames[3].condition.expressions[1].expression).to be_a(BooleanNegation)
        end
      end
    end
  end
end
