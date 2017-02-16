/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai                         from 'chai';
import {test4, test3,test2}                      from '../src/test/TestStrings.js';
import {
    loadDependencies,
    mockDependency
}                               from './Bootstrap.js';

import Parser                   from '../src/Parser.js';
import Form                     from '../src/Form.js';
import Question                 from '../src/statements/Question.js';
import Answer                   from '../src/statements/Answer.js';

const expect = chai.expect;

describe('DataSource', () => {
    let imports = {};

    before(() => loadDependencies({
            // Parser: System.normalizeSync('./src/Parser.js'),
            // Form: System.normalizeSync('./src/Form.js')
        }
        ).then((importedObjects) => {
            imports = importedObjects;
        })
    );




    describe('Parser.parse(test2)', () => {
        it('returns valid AST', () => {
            const parser = new Parser();
            let f = new Form();
            f.name="y";

            expect(parser.parse(test2)).to.deep.equal(f);
        });
    });

    describe('Parser.parse(test3)', () => {
        it('returns valid AST', () => {
            const parser = new Parser();
            let q1 = new Question();
            q1.name = "Q1";
            q1.propertyName = "sellingPrice";
            q1.type = "money";
            let f = new Form([q1]);
            f.name="taxOfficeExample";
            expect(parser.parse(test3,3)).to.deep.equal(f);
        });
    });


    describe('Parser.parse(test4)', () => {
        it('returns valid AST', () => {
            const parser = new Parser();


            let q1 = new Question();
            q1.name = "Q1";
            q1.propertyName = "sellingPrice";
            q1.type = "money";
            let q2 = new Question();
            q2.name = "Q2";
            q2.propertyName = "privateDebt";
            q2.type = "money";

            let a2 = new Answer();
            a2.name= "not defined";
            a2.type= "money";
            a2.propertyName = "A2";
            a2.expression = "(Q1 - Q2)";

            let f = new Form([q1,q2,a2]);
            f.name="taxOfficeExample";
            expect(parser.parse(test4,4)).to.deep.equal(f);
        });
    });
});