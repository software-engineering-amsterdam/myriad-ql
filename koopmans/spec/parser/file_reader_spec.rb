require 'rspec'
require 'parser/file_reader'

describe FileReader do
  let(:reader) { FileReader.new }

  it 'reads file' do
    expect(reader.read_file('examples/simple_questionnaire.ql')).to include('form')
  end
end
