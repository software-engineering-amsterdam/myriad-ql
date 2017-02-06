/**
 * Created by manuel on 06/02/17.
 * Based on Arva-js, open source framework: https://github.com/Bizboard/arva-js/blob/master/test/meta/TestBootstrap.js
 */

import System                   from 'systemjs';
import sinon                    from 'sinon';

export function mockDependency(dependency, replacement) {

    if (!replacement) {
        replacement = sinon.stub();
    }
    if (typeof replacement === 'function') {
        replacement = {default: replacement};
    }

    System.delete(System.normalizeSync(dependency));
    System.set(System.normalizeSync(dependency), System.newModule(replacement));
}

export function restoreDependency(dependency) {
    System.delete(System.normalizeSync(dependency));
}

export function loadDependencies(dependencies) {
    let imports = {};
    let promises = [];

    for (let key in dependencies) {
        let dependencyLocation = dependencies[key];
        promises.push(System.import(dependencyLocation).then((importedObject) => {
            imports[key] = importedObject[key] || importedObject.default || importedObject;
        }));
    }

    return Promise.all(promises).then(() => {
        return imports;
    });
}