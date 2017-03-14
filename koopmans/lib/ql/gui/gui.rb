require 'tk'

module QL
  module GUI
    class GUI
      attr_writer :question_frames

      def initialize(question_frames=nil)
        @question_frames = question_frames
      end

      def run
        check_notifications
        render_questions
        render_submit_button
        Tk.mainloop
      end

      def render_questions
        @question_frames.each do |question_frame|
          question_frame.render
        end

        @question_frames.each do |question_frame|
          question_frame.listen do
            reload_questions
          end
        end

        reload_questions
      end

      def render_submit_button
        submit_button = SubmitButton.new
        submit_button.listen do
          print_form
        end
      end

      def reload_questions
        @question_frames.each(&:reload)
      end

      def print_form
        pp enabled_questions.map(&:to_json)
      end

      def enabled_questions
        @question_frames.each.select(&:enabled)
      end

      def check_notifications

      end
    end
  end
end










# return if check(type_checker) == 'quit'

# TODO hier wat aan doen
# def check(type_checker)
#   if !type_checker[:errors].empty?
#     Tk.messageBox(
#       type:    'ok',
#       icon:    'error',
#       title:   'Errors found!',
#       message: type_checker[:errors].map(&:message).join('\n')
#     )
#     return 'quit'
#   elsif !type_checker[:warnings].empty?
#     Tk.messageBox(
#       type:    'ok',
#       icon:    'warning',
#       title:   'Warnings found!',
#       message: type_checker[:warnings].map(&:message).join('\n')
#     )
#     return 'continue'
#   end
# end