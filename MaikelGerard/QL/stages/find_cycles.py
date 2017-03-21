from collections import OrderedDict


class FindCycles(object):
    def __init__(self, ast, error_handler):
        self.ast = ast
        self.handler = error_handler
        self.graph = OrderedDict()

    def start_traversal(self):
        self.graph = OrderedDict()
        self.ast.accept(self, [])

        cycle_list = self.find_cycles()
        if len(cycle_list) > 0:
            self.handler.add_cycle_error(cycle_list)

    # Heavily referenced from:
    # http://codereview.stackexchange.com/questions/86021
    def find_cycles(self):
        path = []
        visited = []

        def visit(vertex):
            if vertex in visited:
                return []
            visited.append(vertex)
            path.append(vertex)
            for neighbour in self.graph[vertex]:
                if neighbour in path or len(visit(neighbour)) > 0:
                    return path
            path.remove(vertex)
            return []

        cycle_list = []
        for v in self.graph:
            cycle = visit(v)
            if len(cycle) > 0 and cycle not in cycle_list:
                cycle_list.append(cycle)
        return cycle_list

    @staticmethod
    def question_node(question_node, node_var_list):
        node_var_list.append(question_node.name)

    def comp_question_node(self, comp_question_node, node_var_list):
        node_var_list.append(comp_question_node.name)

        # Check on circular dependency to itself.
        to_vars = []
        from_var = comp_question_node.name
        comp_question_node.expression.accept(self, to_vars)
        if from_var in to_vars:
            self.handler.add_cycle_error([[from_var, from_var]])

    def if_node(self, if_node, _):
        from_vars = []
        if_node.condition.accept(self, from_vars)

        to_vars = []
        if_node.if_block.accept(self, to_vars)
        self.add_edge_relations(from_vars, to_vars)

    def if_else_node(self, if_else_node, _):
        from_vars = []
        if_else_node.condition.accept(self, from_vars)

        to_vars = []
        if_else_node.if_block.accept(self, to_vars)
        if_else_node.else_block.accept(self, to_vars)
        self.add_edge_relations(from_vars, to_vars)

    def add_edge_relations(self, from_vars, to_vars):
        for from_var in from_vars:
            for to_var in to_vars:
                # Ensure all keys are in the graph.
                if to_var not in self.graph:
                    self.graph[to_var] = []
                if from_var not in self.graph:
                    self.graph[from_var] = []

                self.graph[from_var].append(to_var)

    def neg_node(self, neg_node, from_vars):
        neg_node.expression.accept(self, from_vars)

    def min_node(self, min_node, from_vars):
        min_node.expression.accept(self, from_vars)

    def plus_node(self, plus_node, from_vars):
        plus_node.expression.accept(self, from_vars)

    def add_node(self, add_node, from_vars):
        self.search_from_vars(add_node, from_vars)

    def sub_node(self, sub_node, from_vars):
        self.search_from_vars(sub_node, from_vars)

    def mul_node(self, mul_node, from_vars):
        self.search_from_vars(mul_node, from_vars)

    def div_node(self, div_node, from_vars):
        self.search_from_vars(div_node, from_vars)

    def lt_node(self, lt_node, from_vars):
        self.search_from_vars(lt_node, from_vars)

    def lte_node(self, lte_node, from_vars):
        self.search_from_vars(lte_node, from_vars)

    def gt_node(self, gt_node, from_vars):
        self.search_from_vars(gt_node, from_vars)

    def gte_node(self, gte_node, from_vars):
        self.search_from_vars(gte_node, from_vars)

    def eq_node(self, eq_node, from_vars):
        self.search_from_vars(eq_node, from_vars)

    def neq_node(self, neq_node, from_vars):
        self.search_from_vars(neq_node, from_vars)

    def and_node(self, and_node, from_vars):
        self.search_from_vars(and_node, from_vars)

    def or_node(self, or_node, from_vars):
        self.search_from_vars(or_node, from_vars)

    def search_from_vars(self, node, from_vars):
        node.left.accept(self, from_vars)
        node.right.accept(self, from_vars)

    @staticmethod
    def string_node(_, from_vars):
        pass

    @staticmethod
    def int_node(_, from_vars):
        pass

    @staticmethod
    def bool_node(_, from_vars):
        pass

    @staticmethod
    def var_node(var_node, from_vars):
        from_vars.append(var_node.name)

    @staticmethod
    def decimal_node(_, from_vars):
        pass

    @staticmethod
    def date_node(_, from_vars):
        pass
