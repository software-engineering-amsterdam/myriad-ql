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
            let expectedHTML = "<html><div id='Form_y'>y</div></html>";
            expect(codeGen.generate(f)).to.deep.equal(expectedHTML);
        });
    });

    describe('CodeGenerator.generate(form with two questions)', () => {
        it('returns valid html', () => {
            const codeGen = new CodeGenerator();
            let q1 = new Question();
            q1.name = "Q1";
            q1.propertyName = "sellingPrice";
            q1.type = "money";
            let q2 = new Question();
            q2.name = "Q2";
            q2.propertyName = "privateDebt";
            q2.type = "money";

            let form = new Form();
            form.name="taxOfficeExample";
            form.statements = [q1,q2];
            let expectedHTML =
                "<html><div id='Form_taxOfficeExample'>taxOfficeExample</div>" +
                "<div>Q1<input type='number' onchange='clicksellingPrice()' id='sellingPrice'></div>" +
                "<div>Q2<input type='number' onchange='clickprivateDebt()' id='privateDebt'></div>" +
                "</html>";

            expect(codeGen.generate(form)).to.deep.equal(expectedHTML);
        });
    });


    describe('CodeGenerator.generate(form with answer based on 2 questions)', () => {
        it('returns valid html', () => {
            const codeGen = new CodeGenerator();
            let q1 = new Question();
            q1.name = "Q1";
            q1.propertyName = "sellingPrice";
            q1.type = "money";
            let q2 = new Question();
            q2.name = "Q2";
            q2.propertyName = "privateDebt";
            q2.type = "money";

            let a2 = new Answer();
            a2.name= "What is Q1-Q2";
            a2.type= "money";
            a2.propertyName = "A2";
            a2.expression = "(Q1 - Q2)";

            let form = new Form();
            form.name="taxOfficeExample";
            form.statements = [q1,q2,a2];
            let expectedHTML =
                "<html><div id='Form_taxOfficeExample'>taxOfficeExample</div>" +
                "<div>Q1<input type='number' onchange='clicksellingPrice()' id='sellingPrice'></div>" +
                "<div>Q2<input type='number' onchange='clickprivateDebt()' id='privateDebt'></div>" +
                "<div>What is Q1-Q2<input type='number' id='A2'></div>" +
                "</html>";

            expect(codeGen.generate(form)).to.deep.equal(expectedHTML);
        });
    });

});