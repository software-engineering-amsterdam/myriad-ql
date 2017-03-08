module QuestionTable
  @storage = {}

  module_function
  def find(id)
    # p "lookup #{id} #{@storage[id]}"
    @storage[id]
  end

  def store(id, value)
    # p "update #{id} to #{value}"
    @storage[id] = value
  end
end