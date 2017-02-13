#!/bin/bash
path=src/UvA/Gamma/Antlr/QL/;
java -cp /usr/local/lib/antlr-4.6-complete.jar org.antlr.v4.Tool $path/QL.g4 -no-listener -visitor && for i in $path/QL*.java; do sed -i '' -e '1s/^/package UvA.Gamma.Antlr.QL;/' $i; done
