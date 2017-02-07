import UnitTests.testParsing as testParse
import QLParser

if __name__ == "__main__":
    parser = QLParser.QuestionnaireParser()

    testParse.test_parser(parser)
