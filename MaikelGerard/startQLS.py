from QLS.Stages.parser import Parser
from QLS.Stages.printHandler import PrintHandler

if __name__ == '__main__':
    example = """
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
                        default money {
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

    qls_parser = Parser()
    parsedAST = qls_parser.parse(example)
    PrintHandler().print_ast(parsedAST)
