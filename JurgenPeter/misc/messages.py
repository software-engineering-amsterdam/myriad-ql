class Message:

    def __init__(self, text):
        self.text = text

    def __str__(self):
        return "Message: {}".format(self.text)

    @staticmethod
    def print(text):
        print("Message: {}".format(text))

    @property
    def critical(self):
        return False


class ErrorMessage(Message):

    def __str__(self):
        return "\33[31mError: {}\33[39m".format(self.text)

    @staticmethod
    def print(text):
        print("\33[31mError: {}\33[39m".format(text))

    @property
    def critical(self):
        return True


class WarningMessage(Message):

    def __str__(self):
        return "\33[33mWarning: {}\33[39m".format(self.text)

    @staticmethod
    def print(text):
        print("\33[33mWarning: {}\33[39m".format(text))
