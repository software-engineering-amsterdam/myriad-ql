import UnitTests.testParsing as testParse
import QL_Parser

if __name__ == "__main__":
    parser = QL_Parser.QuestionnaireParser()

    testParse.test_parser(parser)
