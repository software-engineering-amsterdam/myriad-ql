from misc.messages import ErrorMessage, WarningMessage


class Visitor:
    pass


class CheckerVisitor(Visitor):
    def error(self, message):
        self.errors.append(ErrorMessage(message))

    def warn(self, message):
        self.errors.append(WarningMessage(message))