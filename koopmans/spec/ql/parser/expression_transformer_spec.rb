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
          expression = { operator: '+', right: IntegerLiteral.new('10') }
          expect(expression_transformer.apply(expression)).to be_a(Add)
        end
      end

      context 'boolean' do
        it 'parses And' do
          expression = { operator: '&&', right: BooleanLiteral.new('true') }
          expect(expression_transformer.apply(expression)).to be_a(And)
        end
      end

      context 'comparison' do
        it 'parses Equality' do
          expression = { operator: '==', right: IntegerLiteral.new('10') }
          expect(expression_transformer.apply(expression)).to be_a(Equal)
        end
      end

      context 'negation' do
        it 'parses boolean' do
          expression = [operator: '!', single: BooleanLiteral.new('true')]
          expect(expression_transformer.apply(expression)).to be_a(BooleanNegation)
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
          expression = [IntegerLiteral.new('5'), { operator: '+', right: IntegerLiteral.new('10') }]
          expect(expression_transformer.apply(expression)).to be_a(Expression)
        end
      end
    end
  end
end
