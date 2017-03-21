require 'tk'

module QL
  module GUI
    class GUI
      attr_writer :question_frames

      def initialize(question_frames = nil)
        @question_frames = question_frames
      end

      def render
        @question_frames.each(&:render)
        @submit_button = SubmitButton.new

        reload_questions
        listen_to_questions
        listen_to_submit_button
        Tk.mainloop
      end

      def reload_questions
        @question_frames.each(&:reload)
      end

      def listen_to_questions
        @question_frames.each do |question_frame|
          question_frame.listen do
            reload_questions
          end
        end
      end

      def listen_to_submit_button
        @submit_button.listen do
          print_form
        end
      end

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
