require 'rspec'
require 'require_all'
require 'pp'

require_all 'lib'

module QL
  module GUI
    include AST

    describe QuestionFrame do
      let(:boolean_question_frame) { QuestionFrame.new(name: 'hasSoldHouse', label: 'label', literal_type: BooleanLiteral, widget_type: RadioWidget) }
      let(:another_boolean_question_frame) { QuestionFrame.new(name: 'hasBoughtHouse', label: 'label', literal_type: BooleanLiteral, widget_type: RadioWidget) }
      let(:question_frame_with_condition) { QuestionFrame.new(name: 'hasSoldHouse', label: 'label', literal_type: BooleanLiteral, widget_type: RadioWidget, condition: BooleanLiteral.new('false')) }

      context 'render' do
        it 'stores default value' do
          another_boolean_question_frame.render
          default_value = another_boolean_question_frame.widget.default_value
          stored = VariableTable.find(another_boolean_question_frame.name)
          expect(stored.value).to be(default_value)
        end

        it 'stores value in table if widget does callback' do
          boolean_question_frame.render
          boolean_question_frame.widget.callback(true)
          stored = VariableTable.find(boolean_question_frame.name)
          expect(stored).to be_a(BooleanLiteral)
          expect(stored.value).to be(true)
        end
      end

      context 'condition evaluation' do
        it 'disables if false' do
          question_frame_with_condition.render
          expect(question_frame_with_condition.enabled).to be(true)
          question_frame_with_condition.reload
          expect(question_frame_with_condition.enabled).to be(false)
        end
      end
    end
  end
end
