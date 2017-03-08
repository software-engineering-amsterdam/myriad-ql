module Notification
  class Notification
    attr_reader :message

    def initialize(message)
      @message = message
    end
  end

  class Error < Notification
  end

  class Warning < Notification
  end
end