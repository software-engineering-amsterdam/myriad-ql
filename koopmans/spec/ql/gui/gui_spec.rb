require 'rspec'
require 'require_all'
require 'pp'

require_all 'lib'

module QL
  module GUI
    include AST

    describe GUI do
      let(:ast_question) { Question.new(StringLiteral.new('Did you sell a house in 2010?'), Variable.new('hasSoldHouse'), BooleanType.new) }
      let(:question_frame) { QuestionFrame.new(ast_question) }
      let(:gui) { GUI.new([question_frame]) }

      # context 'submit' do
      #   it 'question frame should have default value' do
      #     thread = Thread.new do
      #       gui.run
      #     end
      #     thread.join
      #   end
      # end
    end
  end
end