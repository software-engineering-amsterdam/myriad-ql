from QLS.Stages.parser import Parser as QLSParser
from QLS.Stages.printHandler import PrintHandler
from QLS.errorHandler import ErrorHandler
from QLS.Stages.typeChecker import TypeChecker

from QL.stages.parser import Parser as QLParser
from QL.environment import Environment
from QL.stages.initEnvironment import InitEnvironment

if __name__ == '__main__':
    example_ql = """
        form taxOfficeExample {
            "Did you sell a house in 2010?"
                hasSoldHouse: boolean
            "Did you buy a house in 2010?"
                hasBoughtHouse: boolean
            "Did you enter a loan?"
                hasMaintLoan: boolean

            if (hasSoldHouse) {
                "What was the selling price?"
                    sellingPrice: integer
                "Private debts for the sold house:"
                    privateDebt: integer
                "Value residue:"
                    valueResidue: integer = (sellingPrice - privateDebt)
            }
        }
        """

    example_qls = """
        stylesheet taxOfficeExample {
            page Housing {
                section "Buying" {
                    question hasBoughtHouse widget checkbox
                }
                section "Loaning" {
                    question hasMaintLoan
                }
            }
            page Selling {
                section "Selling" {
                    question hasSoldHouse
                        widget radio
                    section "You sold a house" {
                        question sellingPrice
                            widget spinbox
                        question privateDebt
                            widget spinbox
                        question valueResidue
                        default integer {
                            width: 400
                            font: "Arial"
                            fontsize: 14
                            color: #999999
                            widget spinbox
                        }
                    }
                }
                default boolean widget radio
            }
        }
    """

    qls_parser = QLSParser()
    qls_ast = qls_parser.parse(example_qls)

    ql_parser = QLParser()
    ql_ast = ql_parser.parse(example_ql)

    error_handler = ErrorHandler()
    ql_env = Environment(error_handler)
    InitEnvironment(ql_ast, ql_env, error_handler).start_traversal()

    TypeChecker(qls_ast, ql_env, error_handler).start_traversal()
    error_handler.check_and_print_errors()

    # print ql_env.variables
    # PrintHandler().print_ast(qls_ast)
