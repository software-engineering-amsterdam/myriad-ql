require 'rspec'
require 'require_all'
require 'pp'

require_all 'lib'

module QL
  module GUI
    include AST

    describe QuestionFrame do
      let(:boolean_ast_question) { Question.new(StringLiteral.new('Did you sell a house in 2010?'), Variable.new('hasSoldHouse'), BooleanType.new) }
      let(:integer_ast_question) { Question.new(StringLiteral.new('What was the selling price?'), Variable.new('sellingPrice'), IntegerType.new) }
      let(:boolean_question_frame) { QuestionFrame.new(boolean_ast_question) }
      let(:integer_question_frame) { QuestionFrame.new(integer_ast_question) }

      context 'create widget' do
        context 'listen to widget' do
          it 'stores value in table' do
            variable_name = boolean_question_frame.variable_name
            widget = boolean_question_frame.create_widget
            boolean_question_frame.listen_to_widget(widget)
            widget.callback(true)
            stored = VariableTable.find(variable_name)

            expect(stored).to be_a(BooleanLiteral)
            expect(stored.to_value).to be(true)
          end
        end
      end

      context 'create widget' do
        context 'do not listen to widget' do
          it 'does not store value in table' do
            variable_name = integer_question_frame.variable_name
            widget = integer_question_frame.create_widget
            widget.callback(true)
            stored = VariableTable.find(variable_name)

            expect(stored).to be(nil)
          end
        end
      end
    end
  end
end