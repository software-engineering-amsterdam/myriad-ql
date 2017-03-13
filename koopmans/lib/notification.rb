module Notification
  class Notification
    attr_reader :message

    def initialize(message)
      @message = message
    end

    def accept(_)
    end
  end

  class Error < Notification
  end

  class Warning < Notification
  end
end