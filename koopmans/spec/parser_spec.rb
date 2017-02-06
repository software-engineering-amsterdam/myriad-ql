require 'rspec'
require 'parslet/rig/rspec'
require 'parser'

describe Parser do
  let(:parser) { Parser.new }

  context 'label' do
    it 'should parse' do
      expect(parser.label).to parse('"How much is?"')
    end
  end

  context 'variable' do
    it 'should parse' do
      expect(parser.variable).to parse('hasSoldHouse:')
    end
  end

  context 'type' do
    it 'should parse' do
      expect(parser.type).to parse('boolean')
    end
  end

  context 'question' do
    it 'should parse' do
      expect(parser.question).to parse('"How much is?" hasSoldHouse: boolean')
    end
  end
end