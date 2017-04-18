require 'require_all'
require 'pp'
require_rel %w(../notification ../notification_table ast gui parser type_checker)

module QL
  class QL
    attr_reader :content, :parse_tree, :ast, :gui

    def build(filename)
      @content = read_file(filename)
      @parse_tree = parse_form
      @ast = transform_form
      check_types
      @gui = build_gui
    end

    def read_file(file_name)
      File.read(file_name)
    rescue
      error = Notification::Error.new('Error while reading QL file')
      NotificationTable.store(error)
      'form _ {}'
    end

    def parse_form
      Parser::FormParser.new.parse(content)
    rescue
      error = Notification::Error.new('Error while creating parse tree')
      NotificationTable.store(error)
      { form: { id: '_', body: {} } }
    end

    def transform_form
      Parser::FormTransformer.new.apply(parse_tree)
    rescue
      error = Notification::Error.new('Error while creating AST')
      NotificationTable.store(error)
      AST::Form.new('_', [])
    end

    def check_types
      TypeChecker::TypeChecker.new.check(ast)
    rescue
      error = Notification::Error.new('Error while type checking')
      NotificationTable.store(error)
    end

    def build_gui
      form_builder = GUI::FormBuilder.new
      ast.accept(form_builder)
      GUI::GUI.new(form_builder.question_frames)
    rescue
      error = Notification::Error.new('Error while building gui')
      NotificationTable.store(error)
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
      error = Notification::Error.new('Runtime error')
      NotificationTable.store(error)
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
