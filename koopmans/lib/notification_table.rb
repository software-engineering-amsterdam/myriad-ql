module NotificationTable
  @storage = []

  module_function
  def index
    @storage
  end

  def store(notification)
    @storage << notification
  end
end