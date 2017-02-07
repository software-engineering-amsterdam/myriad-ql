/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai                         from 'chai';
import {
    loadDependencies,
    mockDependency
}                                   from './Bootstrap.js';

let expect = chai.expect;

describe('DataSource', () => {
    let imports = {};

    before(() => loadDependencies({
            Parser: System.normalizeSync('./src/Parser.js'),
            Form: System.normalizeSync('./src/Form.js')
        }
        ).then((importedObjects) => {
            imports = importedObjects;
        })
    );

    describe('Parser.parse', () => {
        it('returns valid parsed text', () => {
            let parser = new imports.Parser();
            let form = new imports.Form("My valid input");
            //parser.lexer("");

            // TODO make actual input
            expect(parser.parse('My valid input')._name).to.equal(form._name);
        });
    });
});