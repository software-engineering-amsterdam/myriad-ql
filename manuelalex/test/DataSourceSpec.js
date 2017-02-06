/**
 * Created by Manuel on 06/02/2017.
 */

import chai                         from 'chai';
import {
    loadDependencies,
    mockDependency
}                                   from './Bootstrap.js';

let should = chai.should();

describe('DataSource', () => {
    let imports = {};

    before(() => loadDependencies({
            DataSource: System.normalizeSync('./src/DataSource.js')
        }
        ).then((importedObjects) => {
            imports = importedObjects;
        })
    );

    describe('#constructor', () => {
        it('constructs without exceptions', () => {
            let instance = new imports.DataSource();
            should.exist(instance);
        });
    });
});