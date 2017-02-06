require 'rspec'
require 'parslet/rig/rspec'
require 'parser'

describe Parser do
  let(:parser) { Parser.new }
  context 'one question' do
    it 'should parse' do
      expect(parser.question).to parse('"How much is?" hasSoldHouse: boolean')
    end
  end
end