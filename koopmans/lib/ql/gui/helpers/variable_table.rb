module QL
  module GUI
    module VariableTable
      @storage = {}

      module_function

      def find(name)
        @storage[name]
      end

      def store(name, value)
        @storage[name] = value
      end
    end
  end
end