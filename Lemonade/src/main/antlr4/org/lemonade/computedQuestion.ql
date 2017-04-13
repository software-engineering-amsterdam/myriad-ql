form computedNest {
    test1 : "5" integer
    test3 : "5" integer
    test4: "10" boolean
    testComputed: "Werk ik?" integer = test4
    if ((test4 == (test1 + test4))) {
        testNest : "Blijkbaar?" integer = test1+testNest
    }
    testUnnest : "Unnest?" boolean
 }
