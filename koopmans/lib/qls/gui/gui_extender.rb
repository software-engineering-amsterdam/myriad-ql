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
          p @stylesheet[question_frame.name]
        end
      end
    end
  end
end