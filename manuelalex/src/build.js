(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports) :
    typeof define === 'function' && define.amd ? define(['exports'], factory) :
    (factory((global.Parser = global.Parser || {})));
}(this, (function (exports) { 'use strict';

/**
 * Created by alexvanmanen on 06-02-17.
 */


class Parser {

    parse(text){

        return text;
    }
}

exports.Parser = Parser;

Object.defineProperty(exports, '__esModule', { value: true });

})));

//# sourceMappingURL=build.js.map