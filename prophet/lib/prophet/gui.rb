module Prophet
  class Gui
    def initialize(ast)
      @ast = ast
      @state = {}
      @evaluator = Evaluator.new(ast, state)
      @widgets = []
      @window = TkRoot.new { title 'Prophet' }
    end

    def render
      submit = TkButton.new(window).pack
      submit.text = 'Refresh'
      submit.command do
        save_state
        clear_window
        load_form
      end

      build_initial_state
      load_form

      Tk.mainloop
    end

    private

    attr_reader :ast, :evaluator, :state, :window, :widgets

    def clear_window
      TkWinfo.children(window).select do |child|
        child.is_a? Tk::Frame
      end.each(&:destroy)
    end

    def save_state
      @widgets.each do |widget|
        state[widget.name] = widget.value
      end
    end

    def load_form
      frame = TkFrame.new(window).pack

      values = evaluator.evaluate

      @widgets = ast.visit Visitors::GuiBuilder.new(values)
      widgets.each { |widget| widget.render frame }
    end

    def build_initial_state
      ast.select_by_type(:question, :question_with_value).each do |question|
        state[question.identifier.name.to_s] = question.type.default_value
      end
    end
  end
end
