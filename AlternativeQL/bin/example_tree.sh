#!/bin/bash
antlr4 src/org/uva/sea/ql/parser/QL.g4 -o generated && \
    javac generated/src/org/uva/sea/ql/parser/*.java -d out && \
    cd out && \
    grun QL form ../drafts/*.aql -gui && \
    cd ..
