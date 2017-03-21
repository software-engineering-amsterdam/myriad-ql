module QL
  module GUI
    class SubmitButton
      include Callback

      def render
        button = TkButton.new.grid
        button.text = 'Submit'
        button.command = proc { callback }
      end
    end
  end
end
