#!/bin/bash
java -cp /usr/local/lib/antlr-4.6-complete.jar org.antlr.v4.Tool QL/QL.g4 -no-listener -visitor && for i in QL/QL*.java; do sed -i '' -e '1s/^/package UvA.Gamma.Antlr.QL;/' $i; done
