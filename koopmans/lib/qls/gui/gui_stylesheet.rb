module QLS
  module GUI
    module GUIStylesheet
      attr_writer :stylesheet

      def render
        set_widgets
        super
      end

      def set_widgets
        normal_question_frames.each do |question_frame|
          new_widget = @stylesheet[question_frame.name]
          next if new_widget.nil?
          question_frame.widget = new_widget
        end
      end

      def normal_question_frames
        @question_frames.each.select do |question_frame|
          question_frame.class == QL::GUI::QuestionFrame
        end
      end
    end
  end
end