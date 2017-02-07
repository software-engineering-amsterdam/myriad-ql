#!/bin/bash
cd src/org/ql/parser && \
    antlr4 QL.g4 -o ../../../../gen/org/ql/parser -package org.ql.parser && \
    cd ../../../../
    javac  gen/org/ql/parser/*.java -d out && \
    cd out && \
    grun org.ql.parser.QL form ../drafts/ExampleForm.aql -gui && \
    cd ..
