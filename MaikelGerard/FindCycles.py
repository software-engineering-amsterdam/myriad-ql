import networkx as nx


class FindCycles(object):
    def __init__(self, ast, error_handler):
        """ First create a directed graph from all edges, then check on cycles.
            :type ast: AST.QuestionnaireAST
            :type error_handler: ErrorHandler.ErrorHandler
        """
        self.ast = ast
        self.handler = error_handler
        self.context = "FindCycles"
        self.directed_graph = nx.DiGraph()
        self.node_stack = []

    def start_traversal(self):
        # Ensure the environment and error log are empty.
        self.directed_graph.clear()
        self.node_stack = []
        self.handler.clear_errors()

        self.ast.root.accept(self)
        cycle_list = list(nx.simple_cycles(self.directed_graph))
        if len(cycle_list) > 0:
            self.handler.add_cycle_error(self.context, cycle_list)

        # Print errors afterwards.
        self.handler.print_errors()

    def question_node(self, question_node):
        self.node_stack[-1].append(question_node.name.val)

    def comp_question_node(self, comp_question_node):
        self.node_stack[-1].append(comp_question_node.name.val)

    def add_edge_relations(self, from_vars, to_vars):
        for from_var in from_vars:
            for to_var in to_vars:
                self.directed_graph.add_edge(from_var, to_var)

    def traverse_branch(self, node, node_branch):
        self.node_stack.append([])
        node_branch.accept(node)
        return self.node_stack.pop()

    def if_node(self, if_node):
        """ :type if_node: AST.IfNode """
        from_vars = if_node.expression.accept(self)
        if from_vars is None:
            return

        to_vars = self.traverse_branch(self, if_node.if_block)
        self.add_edge_relations(from_vars, to_vars)

    def if_else_node(self, if_else_node):
        """ :type if_else_node: AST.IfElseNode """
        from_vars = if_else_node.expression.accept(self)
        if from_vars is None:
            return

        to_vars = self.traverse_branch(self, if_else_node.if_block)
        self.add_edge_relations(from_vars, to_vars)

        to_vars = self.traverse_branch(self, if_else_node.else_block)
        self.add_edge_relations(from_vars, to_vars)

    def mon_op_node(self, node):
        return node.expression.accept(self)

    def neg_node(self, neg_node):
        return self.mon_op_node(neg_node)

    def min_node(self, min_node):
        return self.mon_op_node(min_node)

    def plus_node(self, plus_node):
        return self.mon_op_node(plus_node)

    def combine_from_var_list(self, node):
        left = node.left.accept(self)
        right = node.right.accept(self)
        if left is None and right is None:
            return None
        elif left is not None and right is not None:
            return left + right
        elif left is not None:
            return left
        elif right is not None:
            return right
        assert False, "Invalid state in find cycles!"

    def add_node(self, add_node):
        return self.combine_from_var_list(add_node)

    def sub_node(self, sub_node):
        return self.combine_from_var_list(sub_node)

    def mul_node(self, mul_node):
        return self.combine_from_var_list(mul_node)

    def lt_node(self, lt_node):
        return self.combine_from_var_list(lt_node)

    def lte_node(self, lte_node):
        return self.combine_from_var_list(lte_node)

    def gt_node(self, gt_node):
        return self.combine_from_var_list(gt_node)

    def gte_node(self, gte_node):
        return self.combine_from_var_list(gte_node)

    def eq_node(self, eq_node):
        return self.combine_from_var_list(eq_node)

    def neq_node(self, neq_node):
        return self.combine_from_var_list(neq_node)

    def and_node(self, and_node):
        return self.combine_from_var_list(and_node)

    def or_node(self, or_node):
        return self.combine_from_var_list(or_node)

    @staticmethod
    def string_node(_):
        return None

    @staticmethod
    def int_node(_):
        return None

    @staticmethod
    def bool_node(_):
        return None

    @staticmethod
    def var_node(var_node):
        return [var_node.val]

    @staticmethod
    def decimal_node(_):
        return None

    @staticmethod
    def date_node(_):
        return None
