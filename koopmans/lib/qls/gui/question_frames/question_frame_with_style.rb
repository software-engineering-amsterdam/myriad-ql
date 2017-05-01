module QLS
  module GUI
    class QuestionFrameWithStyle < SimpleDelegator
      attr_accessor :font

      def initialize(question_frame)
        @question_frame = question_frame
        super
      end

      def apply_style(style)
        return unless style
        apply_width(style.width)
        apply_style_to_label(style)

        return if computed_question?
        apply_widget(style.widget)
      end

      def apply_widget(widget)
        return unless widget
        @question_frame.widget = widget
      end

      def apply_width(width)
        return unless width
        @question_frame.tk_frame.padx = width / 2
      end

      def apply_style_to_label(style)
        @question_frame.label = LabelWithStyle.new(@question_frame.label)
        @question_frame.label.apply_style(style)
      end

      def computed_question?
        @question_frame.class == QL::GUI::ComputedQuestionFrame
      end
    end
  end
end
