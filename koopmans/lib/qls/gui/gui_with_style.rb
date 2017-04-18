module QLS
  module GUI
    class GUIWithStyle < SimpleDelegator
      attr_writer :question_frame_styles

      def initialize(gui)
        @gui = gui
        super
      end

      def render
        apply_styles
        super
      end

      def apply_styles
        return unless @question_frame_styles

        @gui.question_frames.map! do |question_frame|
          question_frame = QuestionFrameWithStyle.new(question_frame)
          style = find_style(question_frame)
          question_frame.apply_style(style)
          question_frame
        end
      end

      def find_style(question_frame)
        @question_frame_styles[question_frame.name]
      end
    end
  end
end
