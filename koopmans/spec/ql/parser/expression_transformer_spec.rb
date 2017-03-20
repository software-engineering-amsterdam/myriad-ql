require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QL
  module Parser
    include AST
    describe ExpressionTransformer do
      let(:expression_transformer) { ExpressionTransformer.new }

      context 'arithmetic' do
        it 'parses Add' do
          expression = { arithmetic_operator: '+', right: IntegerLiteral.new('10') }
          expect(expression_transformer.apply(expression)).to be_a(ArithmeticExpression)
        end
      end

      context 'boolean' do
        it 'parses And' do
          expression = { boolean_operator: '&&', right: BooleanLiteral.new('true') }
          expect(expression_transformer.apply(expression)).to be_a(BooleanExpression)
        end
      end

      context 'comparison' do
        it 'parses Equality' do
          expression = { comparison_equal_operator: '==', right: IntegerLiteral.new('10') }
          expect(expression_transformer.apply(expression)).to be_a(ComparisonEqualExpression)
        end

        it 'parses Greater' do
          expression = { comparison_order_operator: '>', right: IntegerLiteral.new('10') }
          expect(expression_transformer.apply(expression)).to be_a(ComparisonOrderExpression)
        end
      end

      context 'negation' do
        it 'parses boolean' do
          expression = [negation_operator: '!', single: BooleanLiteral.new('true')]
          expect(expression_transformer.apply(expression)).to be_a(BooleanNegation)
        end

        it 'parses integer' do
          expression = [negation_operator: '-', single: IntegerLiteral.new('10')]
          expect(expression_transformer.apply(expression)).to be_a(IntegerNegation)
        end
      end

      context 'left' do
        it 'parses integer' do
          expression = { left: IntegerLiteral.new('10') }
          expect(expression_transformer.apply(expression)).to be_a(IntegerLiteral)
        end
      end

      context 'expression' do
        it 'parses' do
          expression = [IntegerLiteral.new('5'), { arithmetic_operator: '+', right: IntegerLiteral.new('10') }]
          expect(expression_transformer.apply(expression)).to be_a(Expression)
        end
      end
    end
  end
end
