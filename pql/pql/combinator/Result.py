class Result:
    def __init__(self, value, pos):
        self.value = value
        self.pos = pos

    def __repr__(self):
        return 'Result(value: %s, pos: %d)' % (self.value, self.pos)
