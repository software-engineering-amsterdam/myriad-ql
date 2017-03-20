require 'tk'

module QL
  module GUI
    class GUI
      # TODO question_frames class? ( First class collections)
      attr_writer :question_frames

      def initialize(question_frames=nil)
        @question_frames = question_frames
      end

      def render
        render_questions
        listen_to_questions

        render_submit_buttom
        listen_to_submit_button
        Tk.mainloop
      end

      def render_questions
        @question_frames.each do |question_frame|
          question_frame.render
        end
        reload_questions
      end

      def listen_to_questions
        @question_frames.each do |question_frame|
          question_frame.listen do
            reload_questions
          end
        end
      end

      def reload_questions
        @question_frames.each(&:reload)
      end

      def render_submit_buttom
        @submit_button = SubmitButton.new
      end

      def listen_to_submit_button
        @submit_button.listen do
          print_form
        end
      end

      # TODO Open/closed principle formatting (https://subvisual.co/blog/posts/19-solid-principles-in-ruby)
      def print_form
        write_to_file(enabled_questions.map(&:print).join("\n"))
      end

      def write_to_file(body)
        File.open('results.txt', 'w') { |file| file.write(body) }
      end

      def enabled_questions
        @question_frames.each.select(&:enabled)
      end
    end
  end
end