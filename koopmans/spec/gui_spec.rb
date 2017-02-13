require 'rspec'
require 'gui'
require 'pp'

describe Gui do
  let(:gui) { Gui.new }

  context 'question' do
    it 'parses' do
      p gui
      # expect(parser.label).to parse('"How much is?"')
    end
  end
end
