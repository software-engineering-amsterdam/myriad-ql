require 'rspec'
require 'require_all'
require 'pp'

require_all 'lib'

module QL
  module GUI
    include AST

    describe ComputedQuestionFrame do
      let(:computed_question_frame) { ComputedQuestionFrame.new(name: 'hasSoldHouse', label: 'label', literal_type: IntegerLiteral, widget_type: ComputedWidget, assignment: IntegerLiteral.new(1)) }
      let(:integer_question_frame) { QuestionFrame.new(name: 'sellingPrice', label: 'label', literal_type: IntegerLiteral, widget_type: SpinboxWidget) }
      let(:computed_question_frame_with_dependency) { ComputedQuestionFrame.new(name: 'hasSoldHouse', label: 'label', literal_type: IntegerLiteral, widget_type: ComputedWidget, assignment: Variable.new('sellingPrice')) }

      context 'render' do
        it 'stores computed value' do
          computed_question_frame.render
          computed_question_frame.compute
          stored = VariableTable.find(computed_question_frame.name)
          expect(stored.value).to be(1)
        end
      end

      context 'computed questions that is dependent on other question' do
        it 'changes' do
          question_frames = [integer_question_frame, computed_question_frame_with_dependency]
          question_frames.map(&:render)
          integer_question_frame.widget.callback(2)
          question_frames.map(&:reload)
          stored = VariableTable.find(computed_question_frame_with_dependency.name)
          expect(stored.value).to be(2)
        end
      end
    end
  end
end