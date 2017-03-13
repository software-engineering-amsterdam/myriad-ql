class Expression(object):
    def __init__(self, operation, lnode, rnode):
        # if lnode.type != rnode.type:
        #    raise Exception('Invalid node')

        self.operation = operation
        self.lnode = lnode
        self.rnode = rnode
        self.type = None

    def read(self, context):
        pass

    def depends_on(self):
        return self.lnode.depends_on() + self.rnode.depends_on()

    def __str__(self):
        return '{} {} {}'.format(self.lnode, self.operation,
                                 self.rnode)


class AndExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('&&', lnode, rnode)
        self.type = 'boolean'

    def read(self, context):
        return self.lnode.read(context) and self.rnode.read(context)


class OrExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('||', lnode, rnode)
        self.type = 'boolean'

    def read(self, context):
        return self.lnode.read(context) or self.rnode.read(context)


class LTExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('<', lnode, rnode)
        self.type = 'boolean'

    def read(self, context):
        return self.lnode.read(context) < self.rnode.read(context)


class LETExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('<=', lnode, rnode)
        self.type = 'boolean'

    def read(self, context):
        return self.lnode.read(context) <= self.rnode.read(context)


class GTExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('>', lnode, rnode)
        self.type = 'boolean'

    def read(self, context):
        return self.lnode.read(context) > self.rnode.read(context)


class GETExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('>=', lnode, rnode)
        self.type = 'boolean'

    def read(self, context):
        return self.lnode.read(context) >= self.rnode.read(context)


class NEQExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('!=', lnode, rnode)
        self.type = 'boolean'

    def read(self, context):
        return self.lnode.read(context) != self.rnode.read(context)


class EQExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('==', lnode, rnode)
        self.type = 'boolean'

    def read(self, context):
        return self.lnode.read(context) == self.rnode.read(context)


class PlusExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('+', lnode, rnode)
        self.type = 'decimal'

    def read(self, context):
        return self.lnode.read(context) + self.rnode.read(context)


class MinusExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('-', lnode, rnode)
        self.type = 'decimal'

    def read(self, context):
        return self.lnode.read(context) - self.rnode.read(context)


class MultExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('*', lnode, rnode)
        self.type = 'decimal'

    def read(self, context):
        return self.lnode.read(context) * self.rnode.read(context)


class DivExpression(Expression):
    def __init__(self, lnode, rnode):
        super().__init__('/', lnode, rnode)
        self.type = 'decimal'

    def read(self, context):
        if self.rnode.read(context) == 0.0:
            return 0.0
        else:
            return self.lnode.read(context) / self.rnode.read(context)


class IdExpression(Expression):
    def __init__(self, id):
        super().__init__('id', None, None)
        self.id = id

    def read(self, context):
        return context.get_value(self.id)

    def depends_on(self):
        return [self.id]

    def get_type(self, context):
        return context.get_type(self.id)

    def __str__(self):
        return '{}'.format(self.id)
