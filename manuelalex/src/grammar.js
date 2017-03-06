import {FormPostProcessor as FP} from './processors/FormPostProcessor.js'; var FormPostProcessor = new FP();
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
var grammar = {
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
    {"name": "form$string$1", "symbols": [{"literal":"f"}, {"literal":"o"}, {"literal":"r"}, {"literal":"m"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "form$ebnf$1", "symbols": []},
    {"name": "form$ebnf$1", "symbols": ["statement", "form$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "form", "symbols": ["form$string$1", "_", "formName", "_", {"literal":"{"}, "_", "form$ebnf$1", {"literal":"}"}], "postprocess": FormPostProcessor.form},
    {"name": "formName", "symbols": ["word"]},
    {"name": "statement", "symbols": ["question"], "postprocess": FormPostProcessor.statement},
    {"name": "statement", "symbols": ["answer"], "postprocess": FormPostProcessor.statement},
    {"name": "statement", "symbols": ["if_statement"], "postprocess": FormPostProcessor.statement},
    {"name": "statement", "symbols": ["ifelse_statement"], "postprocess": FormPostProcessor.statement},
    {"name": "question$string$1", "symbols": [{"literal":"q"}, {"literal":"u"}, {"literal":"e"}, {"literal":"s"}, {"literal":"t"}, {"literal":"i"}, {"literal":"o"}, {"literal":"n"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "question", "symbols": ["question$string$1", "_", {"literal":"'"}, "sentence", {"literal":"'"}, "_", "propertyName", {"literal":":"}, "_", "propertyType", "_"], "postprocess": FormPostProcessor.question},
    {"name": "ifelse_statement", "symbols": ["if_statement", "else_clause"], "postprocess": FormPostProcessor.ifElseStatement},
    {"name": "if_statement$string$1", "symbols": [{"literal":"i"}, {"literal":"f"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "if_statement", "symbols": ["if_statement$string$1", "_", {"literal":"("}, "expression", {"literal":")"}, "if_body"], "postprocess": FormPostProcessor.ifStatement},
    {"name": "if_body$ebnf$1", "symbols": []},
    {"name": "if_body$ebnf$1", "symbols": ["statement", "if_body$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "if_body", "symbols": ["_", {"literal":"{"}, "_", "if_body$ebnf$1", {"literal":"}"}, "_"]},
    {"name": "else_clause$string$1", "symbols": [{"literal":"e"}, {"literal":"l"}, {"literal":"s"}, {"literal":"e"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "else_clause$ebnf$1", "symbols": []},
    {"name": "else_clause$ebnf$1", "symbols": ["statement", "else_clause$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "else_clause", "symbols": ["else_clause$string$1", "_", {"literal":"{"}, "_", "else_clause$ebnf$1", {"literal":"}"}, "_"]},
    {"name": "answer$string$1", "symbols": [{"literal":"a"}, {"literal":"n"}, {"literal":"s"}, {"literal":"w"}, {"literal":"e"}, {"literal":"r"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "answer", "symbols": ["answer$string$1", "_", {"literal":"'"}, "sentence", {"literal":"'"}, "_", "allocation", "_"], "postprocess": FormPostProcessor.answer},
    {"name": "allocation$subexpression$1", "symbols": ["expression"]},
    {"name": "allocation", "symbols": ["propertyName", {"literal":":"}, "_", "propertyType", "_", {"literal":"="}, "_", "allocation$subexpression$1"], "postprocess": FormPostProcessor.allocation},
    {"name": "expression", "symbols": ["and_expression"]},
    {"name": "expression$string$1", "symbols": [{"literal":"|"}, {"literal":"|"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "expression", "symbols": ["expression", "_", "expression$string$1", "_", "expression"], "postprocess": FormPostProcessor.expression},
    {"name": "and_expression", "symbols": ["not_expression"]},
    {"name": "and_expression$string$1", "symbols": [{"literal":"&"}, {"literal":"&"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "and_expression", "symbols": ["and_expression", "_", "and_expression$string$1", "_", "and_expression"]},
    {"name": "not_expression", "symbols": ["comparison"]},
    {"name": "not_expression", "symbols": [{"literal":"!"}, "not_expression"]},
    {"name": "comparison", "symbols": ["plus_minus_expression"]},
    {"name": "comparison$subexpression$1", "symbols": [{"literal":"<"}]},
    {"name": "comparison$subexpression$1", "symbols": [{"literal":">"}]},
    {"name": "comparison$subexpression$1$string$1", "symbols": [{"literal":">"}, {"literal":"="}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "comparison$subexpression$1", "symbols": ["comparison$subexpression$1$string$1"]},
    {"name": "comparison$subexpression$1$string$2", "symbols": [{"literal":"<"}, {"literal":"="}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "comparison$subexpression$1", "symbols": ["comparison$subexpression$1$string$2"]},
    {"name": "comparison$subexpression$1$string$3", "symbols": [{"literal":"!"}, {"literal":"="}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "comparison$subexpression$1", "symbols": ["comparison$subexpression$1$string$3"]},
    {"name": "comparison$subexpression$1$string$4", "symbols": [{"literal":"="}, {"literal":"="}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "comparison$subexpression$1", "symbols": ["comparison$subexpression$1$string$4"]},
    {"name": "comparison", "symbols": ["comparison", "_", "comparison$subexpression$1", "_", "comparison"]},
    {"name": "plus_minus_expression", "symbols": ["multiply_divide_expression"]},
    {"name": "plus_minus_expression$subexpression$1", "symbols": [{"literal":"-"}]},
    {"name": "plus_minus_expression$subexpression$1", "symbols": [{"literal":"+"}]},
    {"name": "plus_minus_expression", "symbols": ["plus_minus_expression", "plus_minus_expression$subexpression$1", "plus_minus_expression"]},
    {"name": "multiply_divide_expression", "symbols": ["factor"]},
    {"name": "multiply_divide_expression$subexpression$1", "symbols": [{"literal":"/"}]},
    {"name": "multiply_divide_expression$subexpression$1", "symbols": [{"literal":"*"}]},
    {"name": "multiply_divide_expression", "symbols": ["multiply_divide_expression", "multiply_divide_expression$subexpression$1", "multiply_divide_expression"]},
    {"name": "factor", "symbols": ["digits"]},
    {"name": "factor", "symbols": ["propertyName"]},
    {"name": "factor", "symbols": [{"literal":"("}, "expression", {"literal":")"}]},
    {"name": "digits$ebnf$1", "symbols": [/[0-9]/]},
    {"name": "digits$ebnf$1", "symbols": [/[0-9]/, "digits$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "digits", "symbols": ["digits$ebnf$1"], "postprocess": (data)=> Number(data[0])},
    {"name": "propertyName$ebnf$1", "symbols": [/[A-Za-z0-9]/]},
    {"name": "propertyName$ebnf$1", "symbols": [/[A-Za-z0-9]/, "propertyName$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "propertyName", "symbols": ["propertyName$ebnf$1"], "postprocess": function(d) { return d[0].join("") }},
    {"name": "propertyType$string$1", "symbols": [{"literal":"b"}, {"literal":"o"}, {"literal":"o"}, {"literal":"l"}, {"literal":"e"}, {"literal":"a"}, {"literal":"n"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "propertyType", "symbols": ["propertyType$string$1"], "postprocess": FormPostProcessor.boolean},
    {"name": "propertyType$string$2", "symbols": [{"literal":"s"}, {"literal":"t"}, {"literal":"r"}, {"literal":"i"}, {"literal":"n"}, {"literal":"g"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "propertyType", "symbols": ["propertyType$string$2"], "postprocess": FormPostProcessor.string},
    {"name": "propertyType$string$3", "symbols": [{"literal":"i"}, {"literal":"n"}, {"literal":"t"}, {"literal":"e"}, {"literal":"g"}, {"literal":"e"}, {"literal":"r"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "propertyType", "symbols": ["propertyType$string$3"], "postprocess": FormPostProcessor.number},
    {"name": "propertyType$string$4", "symbols": [{"literal":"d"}, {"literal":"a"}, {"literal":"t"}, {"literal":"e"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "propertyType", "symbols": ["propertyType$string$4"], "postprocess": FormPostProcessor.date},
    {"name": "propertyType$string$5", "symbols": [{"literal":"d"}, {"literal":"e"}, {"literal":"c"}, {"literal":"i"}, {"literal":"m"}, {"literal":"a"}, {"literal":"l"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "propertyType", "symbols": ["propertyType$string$5"], "postprocess": FormPostProcessor.number},
    {"name": "propertyType$string$6", "symbols": [{"literal":"m"}, {"literal":"o"}, {"literal":"n"}, {"literal":"e"}, {"literal":"y"}], "postprocess": function joiner(d) {return d.join('');}},
    {"name": "propertyType", "symbols": ["propertyType$string$6"], "postprocess": FormPostProcessor.money},
    {"name": "sentence$ebnf$1", "symbols": [/[ A-Za-z0-9!@#$%^&*()_+\-\=}{\[\]":;?\/>.<,i]/]},
    {"name": "sentence$ebnf$1", "symbols": [/[ A-Za-z0-9!@#$%^&*()_+\-\=}{\[\]":;?\/>.<,i]/, "sentence$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "sentence", "symbols": ["sentence$ebnf$1"], "postprocess": function(d) { return d[0].join("") }},
    {"name": "word$ebnf$1", "symbols": [/[A-Za-z0-9]/]},
    {"name": "word$ebnf$1", "symbols": [/[A-Za-z0-9]/, "word$ebnf$1"], "postprocess": function arrconcat(d) {return [d[0]].concat(d[1]);}},
    {"name": "word", "symbols": ["word$ebnf$1"], "postprocess": function(d) { return d[0].join("") }}
]
  , ParserStart: "form"
}
if (typeof module !== 'undefined'&& typeof module.exports !== 'undefined') {
   module.exports = grammar;
} else {
   window.grammar = grammar;
}
})();
