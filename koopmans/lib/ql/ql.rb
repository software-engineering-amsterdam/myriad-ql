require 'require_all'
require 'pp'
require_rel %w(../notification ../notification_table ast gui parser type_checker)

module QL
  class QL
    attr_reader :content, :parse_tree, :ast, :gui

    def build(filename)
      @content = read_file(filename)
      @parse_tree = form_parser
      @ast = form_transformer
      type_checker
      @gui = gui_builder
    end

    def read_file(file_name)
      File.read(file_name)
    rescue
      NotificationTable.store(Notification::Error.new('Error while reading QL file'))
      'form _ {}'
    end

    def form_parser
      Parser::FormParser.new.parse(content)
    rescue
      NotificationTable.store(Notification::Error.new('Error while creating parse tree'))
      { form: { id: '_', body: {} } }
    end

    def form_transformer
      Parser::FormTransformer.new.apply(parse_tree)
    rescue
      NotificationTable.store(Notification::Error.new('Error while creating AST'))
      AST::Form.new('_', [])
    end

    def type_checker
      TypeChecker::TypeChecker.new.check(ast)
    rescue
      NotificationTable.store(Notification::Error.new('Error while type checking'))
    end

    def gui_builder
      form_builder = GUI::FormBuilder.new
      ast.accept(form_builder)
      GUI::GUI.new(form_builder.question_frames)
    rescue
      NotificationTable.store(Notification::Error.new('Error while building gui'))
    end

    def run_gui
      if NotificationTable.errors.any?
        render_errors
        return
      end
      if NotificationTable.warnings.any?
        render_warnings
      end
      gui.render
    rescue
      NotificationTable.store(Notification::Error.new('Runtime error'))
      render_errors
    end

    def render_errors
      Tk.messageBox(
        type: 'ok',
        icon: 'error',
        title: 'Errors found!',
        message: NotificationTable.errors.map(&:message).join("\n\n")
      )
    end

    def render_warnings
      Tk.messageBox(
        type: 'ok',
        icon: 'warning',
        title: 'Warnings found!',
        message: NotificationTable.warnings.map(&:message).join("\n\n")
      )
    end
  end
end
