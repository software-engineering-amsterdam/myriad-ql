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
          expression_1 = Expression.new([IntegerLiteral.new(5), Add.new(IntegerLiteral.new(10))])
          expect(expression_1.accept(evaluator).to_value).to eq(15)

          # 5 * 10 -> 50
          expression_2 = Expression.new([IntegerLiteral.new(5), Multiply.new(IntegerLiteral.new(10))])
          expect(expression_2.accept(evaluator).to_value).to eq(50)
        end
      end

      context 'boolean' do
        it 'evaluates' do
          # true && false -> false
          expression_1 = Expression.new([BooleanLiteral.new(true), And.new(BooleanLiteral.new(false))])
          expect(expression_1.accept(evaluator).to_value).to eq(false)

          # true || false -> true
          expression_2 = Expression.new([BooleanLiteral.new(true), Or.new(BooleanLiteral.new(false))])
          expect(expression_2.accept(evaluator).to_value).to eq(true)
        end
      end

      context 'comparison' do
        it 'evaluates' do
          # 5 < 10 -> true
          expression_1 = Expression.new([IntegerLiteral.new(5), Less.new(IntegerLiteral.new(10))])
          expect(expression_1.accept(evaluator).to_value).to eq(true)

          # 5 != 10 -> true
          expression_2 = Expression.new([IntegerLiteral.new(5), NotEqual.new(IntegerLiteral.new(10))])
          expect(expression_2.accept(evaluator).to_value).to eq(true)
        end
      end

      context 'negations' do
        it 'evaluates' do
          # !false -> true
          expression_1 = Expression.new(BooleanNegation.new(BooleanLiteral.new(false)))
          expect(expression_1.accept(evaluator).to_value).to eq(true)

          expression_2 = Expression.new(IntegerNegation.new(IntegerLiteral.new(5)))
          expect(expression_2.accept(evaluator).to_value).to eq(-5)
        end
      end
    end
  end
end