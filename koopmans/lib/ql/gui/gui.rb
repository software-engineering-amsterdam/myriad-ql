require 'tk'
@aap = 'asdsd'

# TODO move out of QL module
module QL
  module GUI
    class GUI
      def initialize(ql_ast, qls_ast, type_checker)
        @question_frames = FormBuilder.new(ql_ast).question_frames
        @question_frames.each_with_index do |question_frame, row_position|
          question_frame.render(self, row_position)
        end

        # StylesheetBuilder.new(qls_ast, ql_ast, self)

        row_position = @question_frames.size
        SubmitButton.new(row_position) do
          print_form
        end

        Tk.mainloop
      end

      def reload_questions
        rendered_questions.each(&:reload)
      end

      def print_form
        pp enabled_questions.map(&:to_json)
      end

      def enabled_questions
        rendered_questions.each.select { |question| question.enabled }
      end

      def rendered_questions
        @question_frames.each.select { |question| question.tk_frame }
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