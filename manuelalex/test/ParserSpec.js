/**
 * Created by Manuel on 06-02-17.
 */
import chai from 'chai';
import transpiledRequire from '../src/utils/TranspiledRequire.js';
import JspmImport from './JspmImport.js';
import {testStrings} from './testStrings.js';

const expect = chai.expect;
const assert = chai.assert;

describe('Parser', () => {

    let nearleyParser;
    let parser;

    beforeEach('Rebuild the parser grammar', () => {
        let exec = require('child_process').exec;
        exec('nearleyc src/grammar/grammar.ne -o src/grammar/grammar.js');
    });

    beforeEach('Rebuild the parser generator', (done)=>{
        Promise.all([JspmImport('./src/ast/ASTBuilder.js')]).then(([{ ASTBuilder }]) => {
            let { Parser } = transpiledRequire('src/Parser.js');
            let nearley = require('nearley');
            global.ASTBuilder = ASTBuilder;
            let window = global;
            let grammar = require('../src/grammar/grammar.js');
            parser = new Parser();
            nearleyParser = parser.createParser(nearley, grammar);
            done();
        });
    });

    describe('Parser', () => {
        let validStrings = testStrings.valid;
        let invalidStrings = testStrings.invalid;

        Object.keys(validStrings).forEach((key)=>{
            let testString = validStrings[key];
            it('Parses the valid string: '+ key + ' without errors ', function() {
                let {errors} = parser.parse(testString, nearleyParser);
                expect(errors).to.be.empty;
            });
        });

        Object.keys(invalidStrings).forEach((key)=>{
            let testString = invalidStrings[key];
            it('Parses the invalid string: '+ key + ' with errors ', function() {
                let {errors} = parser.parse(testString, nearleyParser);
                expect(errors).to.not.be.empty;
            });
        });
    });
});