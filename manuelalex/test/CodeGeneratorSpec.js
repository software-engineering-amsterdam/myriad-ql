/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai                         from 'chai';
import {test4, test3,test2}                      from '../src/test/TestStrings.js';
import {
    loadDependencies,
    mockDependency
}                               from './Bootstrap.js';

import CodeGenerator            from '../src/CodeGenerator.js';
import Form                     from '../src/Form.js';
import Question                 from '../src/statements/Question.js';
import Answer                   from '../src/statements/Answer.js';

const expect = chai.expect;

describe('DataSource', () => {
    let imports = {};

    before(() => loadDependencies({
        }
        ).then((importedObjects) => {
            imports = importedObjects;
        })
    );




    describe('CodeGenerator.generate(form y)', () => {
        it('returns valid html', () => {
            const codeGen = new CodeGenerator();
            let f = new Form();
            f.name="y";

            expect(codeGen.generate(f)).to.deep.equal("<html><div id='Form_y'>y</html>");
        });
    });

});