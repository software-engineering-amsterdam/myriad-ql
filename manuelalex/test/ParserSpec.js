/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai                         from 'chai';
import {test3}                      from '../src/test/TestStrings.js';
import {
    loadDependencies,
    mockDependency
}                                   from './Bootstrap.js';

import Parser                       from '../src/Parser.js';
import Form                         from '../src/Form.js';

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

    describe('Parser.parse', () => {
        it('returns valid parsed text', () => {
            const form = new Form('My valid input');
            const parser = new Parser();

            console.log(test3);

            // TODO make actual input
            // expect(parser.parse('My valid input')._name).to.equal(form._name);
        });
    });
});