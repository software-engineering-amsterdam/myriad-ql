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

  context 'two questions' do
    it 'should parse' do
      expect(parser.questions).to parse('
        "Did you sell a house in 2010?"
          hasSoldHouse: boolean
        "Did you buy a house in 2010?"
          hasBoughtHouse: boolean
      ')
    end
  end

  context 'three questions' do
    it 'should parse' do
      expect(parser.questions).to parse('
        "Did you sell a house in 2010?"
          hasSoldHouse: boolean
        "Did you buy a house in 2010?"
          hasBoughtHouse: boolean
        "Did you enter a loan?"
          hasMaintLoan: boolean
      ')
    end
  end
end
