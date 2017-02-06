#!/bin/bash
antlr4 QL.g4 && javac src/*.java && grun QL form ../drafts/*.aql -gui