require 'tk'
@aap = 'asdsd'

# TODO move out of QL module
module QL
  module GUI
    class GUI
      attr_accessor :question_frames

      def initialize(ql_ast, qls_ast, type_checker)
        # return if check(type_checker) == 'quit'
        @question_frames = FormBuilder.new(ql_ast).question_frames

        @question_frames.each_with_index do |question_frame, i|
          question_frame.render(self, i)
        end

        # reload_questions
        # FormBuilder.new(ql_ast, self)
        # pp @questions
        # StylesheetBuilder.new(qls_ast, ql_ast, self)

        # SubmitButton.new(self)
        create_submit_button
        Tk.mainloop
      end

      def reload_questions
        rendered_questions.each(&:reload)
      end

      def submit
        pp enabled_questions.map(&:to_json)
      end

      def enabled_questions
        rendered_questions.each.select { |question| question.enabled }
      end

      def rendered_questions
        question_frames.each.select { |question| question.frame }
      end

      def create_submit_button
        position       = question_frames.size + 1
        button         = TkButton.new.grid(row: position)
        button.text    = 'Submit'
        button.command = proc { submit }
      end

      def number_of_questions
        question_frames.size
      end

      # TODO hier wat aan doen
      def check(type_checker)
        if !type_checker[:errors].empty?
          Tk.messageBox(
              type:    'ok',
              icon:    'error',
              title:   'Errors found!',
              message: type_checker[:errors].map(&:message).join('\n')
          )
          return 'quit'
        elsif !type_checker[:warnings].empty?
          Tk.messageBox(
              type:    'ok',
              icon:    'warning',
              title:   'Warnings found!',
              message: type_checker[:warnings].map(&:message).join('\n')
          )
          return 'continue'
        end
      end
    end
  end
end
