/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai from 'chai';
// import Parser from '../src/Parser.js';
import JspmImport       from './JspmImport.js';
import {
    loadDependencies,
    mockDependency
}                               from './Bootstrap.js';

const expect = chai.expect;

describe('Parser', () => {

    describe('Test', ()=>{
       it('returns valid', (done)=>{
           JspmImport('./src/ASTDependencyVisitor.js').then(({astDependencyVisitor})=>{
                console.log(astDependencyVisitor);
               astDependencyVisitor.visitAST({});
                done();
           });
       })
    });
});