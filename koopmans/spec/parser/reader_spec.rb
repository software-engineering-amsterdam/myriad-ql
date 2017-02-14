require 'rspec'
require 'parser/reader'

describe Reader do
  let(:reader) { Reader.new }

  context 'reader' do
    it 'reads file' do
      expect(reader.read_file('examples/simple_questionnaire.ql')).to include("form")
    end
  end
end
