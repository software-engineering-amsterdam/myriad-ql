require 'rspec'
require 'parslet/rig/rspec'
require 'parser/transformer'

describe Transformer do
  let(:transformer) { Transformer.new }

  describe 'literals' do
    context 'boolean' do
      it 'transforms' do
        expect(transformer.apply(boolean: true)).to be_a(BooleanLiteral)
      end
      it 'transforms negation' do
        expect(transformer.apply(boolean_negation: '!', boolean: true)).to be_a(BooleanNegation)
      end
    end

    context 'integer' do
      it 'transforms' do
        expect(transformer.apply(integer: 42)).to be_a(IntegerLiteral)
      end
      it 'transforms negation' do
        expect(transformer.apply(integer_negation: '-', integer: 42)).to be_a(IntegerNegation)
      end
    end

    context 'strings' do
      it 'transforms' do
        expect(transformer.apply(string: '"How much is?"')).to be_a(StringLiteral)
      end
    end
  end

  describe 'variables' do
    it 'transforms' do
      expect(transformer.apply(variable: 'sellingPrice')).to be_a(Variable)
    end

    it 'transforms negation' do
      expect(transformer.apply(negation: '!', variable: 'sellingPrice')).to be_a(BooleanNegation)
      expect(transformer.apply(negation: '-', variable: 'sellingPrice')).to be_a(IntegerNegation)
    end
  end
end
