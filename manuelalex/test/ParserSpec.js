/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai                         from 'chai';
import {test3,test2}                      from '../src/test/TestStrings.js';
import {
    loadDependencies,
    mockDependency
}                                   from './Bootstrap.js';

import Parser                   from '../src/Parser.js';
import Form                     from '../src/Form.js';

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
});