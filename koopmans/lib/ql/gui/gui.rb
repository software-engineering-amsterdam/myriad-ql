require 'tk'
@aap = 'asdsd'

# TODO move out of QL module
module QL
  module GUI
    class GUI
      attr_accessor :questions

      def initialize(ql_ast, qls_ast, type_checker)
        # return if check(type_checker) == 'quit'
        @questions = []
        FormBuilder.new(ql_ast, self)
        # pp @questions
        # StylesheetBuilder.new(qls_ast, ql_ast, self)

        SubmitButton.new(self)
        Tk.mainloop
      end

      def reload_questions
        @questions.each(&:reload)
      end

      def submit
        pp enabled_questions.map(&:to_json)
      end

      def enabled_questions
        @questions.each.select { |question| question.enabled }
      end

      def number_of_questions
        questions.size
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
