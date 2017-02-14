require 'rspec'
require 'parslet/rig/rspec'
require 'ast/ast'


describe Ast do
  let(:ast) { Ast.new }

  describe 'literals' do
    context 'boolean' do
      it 'transforms' do
        expect(ast.apply(boolean: true)).to be_a(BooleanLiteral)
      end
      it 'transforms negation' do
        expect(ast.apply(boolean_negation: '!', boolean: true)).to be_a(BooleanNegation)
      end
    end

    context 'integer' do
      it 'transforms' do
        expect(ast.apply(integer: 42)).to be_a(IntegerLiteral)
      end
      it 'transforms negation' do
        expect(ast.apply(integer_negation: '-', integer: 42)).to be_a(IntegerNegation)
      end
    end

    context 'strings' do
      it 'transforms' do
        expect(ast.apply(string: '"How much is?"')).to be_a(StringLiteral)
      end
    end
  end

  describe 'variables' do
    it 'transforms' do
      expect(ast.apply(variable: 'sellingPrice')).to be_a(Variable)
    end

    it 'transforms negation' do
      expect(ast.apply(negation: '!', variable: 'sellingPrice')).to be_a(BooleanNegation)
      expect(ast.apply(negation: '-', variable: 'sellingPrice')).to be_a(IntegerNegation)
    end
  end
end
