module QLS
  module GUI
    module GUIStylesheet
      attr_writer :stylesheet

      def render
        set_widgets
        super
      end

      def set_widgets
        @question_frames.each do |question_frame|
          new_widget = @stylesheet[question_frame.name]
          next if new_widget.nil? || question_frame.is_a?(QL::GUI::ComputedQuestionFrame)
          question_frame.widget = new_widget
        end
      end
    end
  end
end