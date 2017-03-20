require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QL
  module GUI
    include AST
    include Parser
    describe Evaluator do
      let(:evaluator) { Evaluator.new }

      context 'arithmetic' do
        it 'evaluates' do
          # 5 + 10 -> 15
          expression_1 = ExpressionSequence.new([IntegerLiteral.new(5), ArithmeticExpression.new('+', IntegerLiteral.new(10))])
          expect(expression_1.accept(evaluator).value).to eq(15)

          # 5 * 10 -> 50
          expression_2 = ExpressionSequence.new([IntegerLiteral.new(5), ArithmeticExpression.new('*', IntegerLiteral.new(10))])
          expect(expression_2.accept(evaluator).value).to eq(50)
        end
      end

      context 'boolean' do
        it 'evaluates' do
          # true && false -> false
          expression_1 = ExpressionSequence.new([BooleanLiteral.new(true), BooleanExpression.new('&&', BooleanLiteral.new(false))])
          expect(expression_1.accept(evaluator).value).to eq(false)

          # true || false -> true
          expression_2 = ExpressionSequence.new([BooleanLiteral.new(true), BooleanExpression.new('||', BooleanLiteral.new(false))])
          expect(expression_2.accept(evaluator).value).to eq(true)
        end
      end

      context 'comparison order' do
        it 'evaluates' do
          # 5 < 10 -> true
          expression_1 = ExpressionSequence.new([IntegerLiteral.new(5), ComparisonOrderExpression.new('<', IntegerLiteral.new(10))])
          expect(expression_1.accept(evaluator).value).to eq(true)
        end
      end

      context 'comparison equal' do
        it 'evaluates' do
          # 5 != 10 -> true
          expression_2 = ExpressionSequence.new([IntegerLiteral.new(5), ComparisonEqualExpression.new('!=', IntegerLiteral.new(10))])
          expect(expression_2.accept(evaluator).value).to eq(true)
        end
      end

      context 'boolean negation' do
        it 'evaluates' do
          # !false -> true2
          expression_1 = BooleanNegation.new('!', BooleanLiteral.new(false))
          expect(expression_1.accept(evaluator).value).to eq(true)
        end
      end

      context 'integer negation' do
        it 'evaluates' do
          # -5 -> -5
          expression_2 = IntegerNegation.new('-', IntegerLiteral.new(5))
          expect(expression_2.accept(evaluator).value).to eq(-5)
        end
      end
    end
  end
end
