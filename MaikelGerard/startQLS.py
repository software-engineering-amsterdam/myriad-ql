from QLS.environment import Environment as QLSEnvironment
from QLS.errorHandler import ErrorHandler
from QLS.Stages.parser import Parser as QLSParser
from QLS.Stages.typeChecker import TypeChecker
from QLS.Stages.determineWidgetType import DetermineWidgetType
from QLS.Stages.printHandler import PrintHandler

from QL.environment import Environment as QLEnvironment
from QL.stages.parser import Parser as QLParser
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
    error_handler = ErrorHandler()

    qls_parser = QLSParser()
    qls_ast = qls_parser.parse(example_qls)
    qls_env = QLSEnvironment(error_handler)

    ql_parser = QLParser()
    ql_ast = ql_parser.parse(example_ql)
    ql_env = QLEnvironment(error_handler)

    PrintHandler().print_ast(qls_ast)

    InitEnvironment(ql_ast, ql_env, error_handler).start_traversal()

    TypeChecker(qls_ast, qls_env, ql_env, error_handler).start_traversal()
    error_handler.check_and_print_errors()
    DetermineWidgetType(
        qls_ast, qls_env, ql_env, error_handler
    ).start_traversal()

    # print ql_env.variables
    PrintHandler().print_ast(qls_ast)
