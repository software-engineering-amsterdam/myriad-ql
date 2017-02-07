#!/bin/bash
javac gen/org/ql/parser/*.java -d out && \
    cd out && \
    grun QL form ../drafts/*.aql -gui && \
    cd ..
