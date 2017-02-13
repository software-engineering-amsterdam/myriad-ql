// Generated automatically by nearley
// http://github.com/Hardmath123/nearley
(function () {
function id(x) {return x[0]; }

function nth(n) {
    return function(d) {
        return d[n];
    };
}


function $(o) {
    return function(d) {
        var ret = {};
        Object.keys(o).forEach(function(k) {
            ret[k] = d[o[k]];
        });
        return ret;
    };
}

 let PostProcessor = require('./PostProcessor.js'); var grammar = {
    ParserRules: [
    {"name": "_$ebnf$1", "symbols": []},
    {"name": "_$ebnf$1", "symbols": ["wschar", "_$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "_", "symbols": ["_$ebnf$1"], "postprocess": function(d) {return null;}},
    {"name": "__$ebnf$1", "symbols": ["wschar"]},
    {"name": "__$ebnf$1", "symbols": ["wschar", "__$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "__", "symbols": ["__$ebnf$1"], "postprocess": function(d) {return null;}},
    {"name": "wschar", "symbols": [/[ \t\n\v\f]/], "postprocess": id},
    {"name": "unsigned_int$ebnf$1", "symbols": [/[0-9]/]},
    {"name": "unsigned_int$ebnf$1", "symbols": [/[0-9]/, "unsigned_int$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "unsigned_int", "symbols": ["unsigned_int$ebnf$1"], "postprocess": 
        function(d) {
            return parseInt(d[0].join(""));
        }
        },
    {"name": "int$ebnf$1$subexpression$1", "symbols": [{"literal":"-"}]},
    {"name": "int$ebnf$1$subexpression$1", "symbols": [{"literal":"+"}]},
    {"name": "int$ebnf$1", "symbols": ["int$ebnf$1$subexpression$1"], "postprocess": id},
    {"name": "int$ebnf$1", "symbols": [], "postprocess": function(d) {return null;}},
    {"name": "int$ebnf$2", "symbols": [/[0-9]/]},
    {"name": "int$ebnf$2", "symbols": [/[0-9]/, "int$ebnf$2"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "int", "symbols": ["int$ebnf$1", "int$ebnf$2"], "postprocess": 
        function(d) {
            if (d[0]) {
                return parseInt(d[0][0]+d[1].join(""));
            } else {
                return parseInt(d[1].join(""));
            }
        }
        },
    {"name": "unsigned_decimal$ebnf$1", "symbols": [/[0-9]/]},
    {"name": "unsigned_decimal$ebnf$1", "symbols": [/[0-9]/, "unsigned_decimal$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "unsigned_decimal$ebnf$2$subexpression$1$ebnf$1", "symbols": [/[0-9]/]},
    {"name": "unsigned_decimal$ebnf$2$subexpression$1$ebnf$1", "symbols": [/[0-9]/, "unsigned_decimal$ebnf$2$subexpression$1$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "unsigned_decimal$ebnf$2$subexpression$1", "symbols": [{"literal":"."}, "unsigned_decimal$ebnf$2$subexpression$1$ebnf$1"]},
    {"name": "unsigned_decimal$ebnf$2", "symbols": ["unsigned_decimal$ebnf$2$subexpression$1"], "postprocess": id},
    {"name": "unsigned_decimal$ebnf$2", "symbols": [], "postprocess": function(d) {return null;}},
    {"name": "unsigned_decimal", "symbols": ["unsigned_decimal$ebnf$1", "unsigned_decimal$ebnf$2"], "postprocess": 
        function(d) {
            return parseFloat(
                d[0].join("") +
                (d[1] ? "."+d[1][1].join("") : "")
            );
        }
        },
    {"name": "decimal$ebnf$1", "symbols": [{"literal":"-"}], "postprocess": id},
    {"name": "decimal$ebnf$1", "symbols": [], "postprocess": function(d) {return null;}},
    {"name": "decimal$ebnf$2", "symbols": [/[0-9]/]},
    {"name": "decimal$ebnf$2", "symbols": [/[0-9]/, "decimal$ebnf$2"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "decimal$ebnf$3$subexpression$1$ebnf$1", "symbols": [/[0-9]/]},
    {"name": "decimal$ebnf$3$subexpression$1$ebnf$1", "symbols": [/[0-9]/, "decimal$ebnf$3$subexpression$1$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "decimal$ebnf$3$subexpression$1", "symbols": [{"literal":"."}, "decimal$ebnf$3$subexpression$1$ebnf$1"]},
    {"name": "decimal$ebnf$3", "symbols": ["decimal$ebnf$3$subexpression$1"], "postprocess": id},
    {"name": "decimal$ebnf$3", "symbols": [], "postprocess": function(d) {return null;}},
    {"name": "decimal", "symbols": ["decimal$ebnf$1", "decimal$ebnf$2", "decimal$ebnf$3"], "postprocess": 
        function(d) {
            return parseFloat(
                (d[0] || "") +
                d[1].join("") +
                (d[2] ? "."+d[2][1].join("") : "")
            );
        }
        },
    {"name": "percentage", "symbols": ["decimal", {"literal":"%"}], "postprocess": 
        function(d) {
            return d[0]/100;
        }
        },
    {"name": "jsonfloat$ebnf$1", "symbols": [{"literal":"-"}], "postprocess": id},
    {"name": "jsonfloat$ebnf$1", "symbols": [], "postprocess": function(d) {return null;}},
    {"name": "jsonfloat$ebnf$2", "symbols": [/[0-9]/]},
    {"name": "jsonfloat$ebnf$2", "symbols": [/[0-9]/, "jsonfloat$ebnf$2"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "jsonfloat$ebnf$3$subexpression$1$ebnf$1", "symbols": [/[0-9]/]},
    {"name": "jsonfloat$ebnf$3$subexpression$1$ebnf$1", "symbols": [/[0-9]/, "jsonfloat$ebnf$3$subexpression$1$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "jsonfloat$ebnf$3$subexpression$1", "symbols": [{"literal":"."}, "jsonfloat$ebnf$3$subexpression$1$ebnf$1"]},
    {"name": "jsonfloat$ebnf$3", "symbols": ["jsonfloat$ebnf$3$subexpression$1"], "postprocess": id},
    {"name": "jsonfloat$ebnf$3", "symbols": [], "postprocess": function(d) {return null;}},
    {"name": "jsonfloat$ebnf$4$subexpression$1$ebnf$1", "symbols": [/[+-]/], "postprocess": id},
    {"name": "jsonfloat$ebnf$4$subexpression$1$ebnf$1", "symbols": [], "postprocess": function(d) {return null;}},
    {"name": "jsonfloat$ebnf$4$subexpression$1$ebnf$2", "symbols": [/[0-9]/]},
    {"name": "jsonfloat$ebnf$4$subexpression$1$ebnf$2", "symbols": [/[0-9]/, "jsonfloat$ebnf$4$subexpression$1$ebnf$2"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "jsonfloat$ebnf$4$subexpression$1", "symbols": [/[eE]/, "jsonfloat$ebnf$4$subexpression$1$ebnf$1", "jsonfloat$ebnf$4$subexpression$1$ebnf$2"]},
    {"name": "jsonfloat$ebnf$4", "symbols": ["jsonfloat$ebnf$4$subexpression$1"], "postprocess": id},
    {"name": "jsonfloat$ebnf$4", "symbols": [], "postprocess": function(d) {return null;}},
    {"name": "jsonfloat", "symbols": ["jsonfloat$ebnf$1", "jsonfloat$ebnf$2", "jsonfloat$ebnf$3", "jsonfloat$ebnf$4"], "postprocess": 
        function(d) {
            return parseFloat(
                (d[0] || "") +
                d[1].join("") +
                (d[2] ? "."+d[2][1].join("") : "") +
                (d[3] ? "e" + (d[3][1] || "+") + d[3][2].join("") : "")
            );
        }
        },
    {"name": "dqstring$ebnf$1", "symbols": []},
    {"name": "dqstring$ebnf$1", "symbols": ["dstrchar", "dqstring$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "dqstring", "symbols": [{"literal":"\""}, "dqstring$ebnf$1", {"literal":"\""}], "postprocess": function(d) {return d[1].join(""); }},
    {"name": "sqstring$ebnf$1", "symbols": []},
    {"name": "sqstring$ebnf$1", "symbols": ["sstrchar", "sqstring$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "sqstring", "symbols": [{"literal":"'"}, "sqstring$ebnf$1", {"literal":"'"}], "postprocess": function(d) {return d[1].join(""); }},
    {"name": "btstring$ebnf$1", "symbols": []},
    {"name": "btstring$ebnf$1", "symbols": [/[^`]/, "btstring$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "btstring", "symbols": [{"literal":"`"}, "btstring$ebnf$1", {"literal":"`"}], "postprocess": function(d) {return d[1].join(""); }},
    {"name": "dstrchar", "symbols": [/[^\\"\n]/], "postprocess": id},
    {"name": "dstrchar", "symbols": [{"literal":"\\"}, "strescape"], "postprocess": 
        function(d) {
            return JSON.parse("\""+d.join("")+"\"");
        }
        },
    {"name": "sstrchar", "symbols": [/[^\\'\n]/], "postprocess": id},
    {"name": "sstrchar", "symbols": [{"literal":"\\"}, "strescape"], "postprocess": 
        function(d) {
            return JSON.parse("\""+d.join("")+"\"");
        }
        },
    {"name": "strescape", "symbols": [/["'\\\/bfnrt]/], "postprocess": id},
    {"name": "strescape", "symbols": [{"literal":"u"}, /[a-fA-F0-9]/, /[a-fA-F0-9]/, /[a-fA-F0-9]/, /[a-fA-F0-9]/], "postprocess": 
        function(d) {
            return d.join("");
        }
        },
    {"name": "form$string$1", "symbols": [{"literal":"f"}, {"literal":"o"}, {"literal":"r"}, {"literal":"m"}, {"literal":" "}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "form", "symbols": ["form$string$1", "formName", {"literal":"{"}, "newLine", "statements", "newLine", {"literal":"}"}]},
    {"name": "formName", "symbols": ["letters"]},
    {"name": "statements$ebnf$1", "symbols": []},
    {"name": "statements$ebnf$1", "symbols": ["statement", "statements$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "statements", "symbols": ["statements$ebnf$1"]},
    {"name": "statement", "symbols": ["question"]},
    {"name": "statement", "symbols": ["if_statement"]},
    {"name": "statement", "symbols": ["answer"]},
    {"name": "question", "symbols": ["sentence", {"literal":"?"}, "newLine", "propertyName", {"literal":":"}, "_", "propertyType", "newLine"], "postprocess": PostProcessor.question},
    {"name": "if_statement$string$1", "symbols": [{"literal":"i"}, {"literal":"f"}, {"literal":" "}, {"literal":"("}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "if_statement$string$2", "symbols": [{"literal":")"}, {"literal":" "}, {"literal":"{"}, {"literal":"\n"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "if_statement$string$3", "symbols": [{"literal":"\n"}, {"literal":"}"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "if_statement", "symbols": ["if_statement$string$1", "propertyName", "if_statement$string$2", "statements", "if_statement$string$3"]},
    {"name": "answer$string$1", "symbols": [{"literal":":"}, {"literal":"\n"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "answer", "symbols": ["sentence", "answer$string$1", "allocation"], "postprocess": function(data){console.log(data)}},
    {"name": "allocation$string$1", "symbols": [{"literal":"v"}, {"literal":"a"}, {"literal":"l"}, {"literal":"u"}, {"literal":"e"}, {"literal":"R"}, {"literal":"e"}, {"literal":"s"}, {"literal":"i"}, {"literal":"d"}, {"literal":"u"}, {"literal":"e"}, {"literal":":"}, {"literal":" "}, {"literal":"m"}, {"literal":"o"}, {"literal":"n"}, {"literal":"e"}, {"literal":"y"}, {"literal":" "}, {"literal":"="}, {"literal":" "}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "allocation", "symbols": ["allocation$string$1", "expression", {"literal":"\n"}]},
    {"name": "expression", "symbols": [{"literal":"("}, "propertyName", "_", "operator", "_", "propertyName", {"literal":")"}]},
    {"name": "operator", "symbols": [{"literal":"+"}]},
    {"name": "operator", "symbols": [{"literal":"-"}]},
    {"name": "operator", "symbols": [{"literal":"*"}]},
    {"name": "operator", "symbols": [{"literal":"/"}]},
    {"name": "propertyName", "symbols": ["letters"]},
    {"name": "propertyType$string$1", "symbols": [{"literal":"b"}, {"literal":"o"}, {"literal":"o"}, {"literal":"l"}, {"literal":"e"}, {"literal":"a"}, {"literal":"n"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "propertyType", "symbols": ["propertyType$string$1"]},
    {"name": "propertyType$string$2", "symbols": [{"literal":"m"}, {"literal":"o"}, {"literal":"n"}, {"literal":"e"}, {"literal":"y"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "propertyType", "symbols": ["propertyType$string$2"]},
    {"name": "newLine", "symbols": [{"literal":"\n"}], "postprocess": PostProcessor.toNull},
    {"name": "sentence$ebnf$1", "symbols": [/[\w|\s]/]},
    {"name": "sentence$ebnf$1", "symbols": [/[\w|\s]/, "sentence$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "sentence", "symbols": ["sentence$ebnf$1"], "postprocess": PostProcessor.toString},
    {"name": "letters$ebnf$1", "symbols": [/[a-zA-Z]/]},
    {"name": "letters$ebnf$1", "symbols": [/[a-zA-Z]/, "letters$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "letters", "symbols": ["letters$ebnf$1"], "postprocess": PostProcessor.toString}
]
  , ParserStart: "form"
}
if (typeof module !== 'undefined'&& typeof module.exports !== 'undefined') {
   module.exports = grammar;
} else {
   window.grammar = grammar;
}
})();
