module NotificationTable
  @storage = []

  module_function

  def index
    @storage
  end

  def errors
    @storage.select { |notification| notification.is_a?(Notification::Error) }
  end

  def warnings
    @storage.select { |notification| notification.is_a?(Notification::Warning) }
  end

  def store(notification)
    @storage << notification
  end
end
