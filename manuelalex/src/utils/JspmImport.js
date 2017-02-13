let jspm = require('jspm');
let SystemJS = require('systemjs');
let config = require('../../jspm.config.js');

module.exports = function jspmImport(dependency) {
    let module = SystemJS.import(dependency);
    return module.default || module;
};