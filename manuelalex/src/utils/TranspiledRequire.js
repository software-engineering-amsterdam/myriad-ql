let babel = require('babel-core');
let internalModule = require('internal/module');
let requireFromString = require('require-from-string');

let babelPlugins = [
    'babel-plugin-transform-es2015-modules-commonjs',
    'babel-plugin-transform-decorators-legacy',
    'babel-plugin-transform-class-properties'
];

/**
 *
 * @param {String} path
 * @param {String} [parentPath] Optional: parent path to mark the above path as relative to
 */
function transpiledRequire(path = '', parentPath = '') {
    /* Transpile the file contents with Babel */
    let code = babel.transformFileSync(path, {plugins: babelPlugins}).code;

    /* Override nodejs' require() method, to make imports that get transpiled into
     * calls to require() use our transpiledRequire() method instead. This enables us
     * to have babelified ES6 modules that also import other such modules. */
    let oldMakeRequireFunction = internalModule.makeRequireFunction;
    internalModule.makeRequireFunction = function () {return hookedMakeRequireFunction(parentPath);};

    /* Compile our code into a nodejs module */
    let result = requireFromString(code, parentPath);

    /* Put back the original require() method */
    internalModule.makeRequireFunction = oldMakeRequireFunction;
    return result;
}

/* Based on node/lib/internal/module.js:makeRequireFunction() */
function hookedMakeRequireFunction(filename = '') {
    const Module = this.constructor;
    const self = this;

    function require(path) {
        return transpiledRequire(path, filename);
    }

    function resolve(request) {
        return Module._resolveFilename(request, self);
    }

    require.resolve = resolve;

    require.main = process.mainModule;

    // Enable support to add extra extension types.
    require.extensions = Module._extensions;

    require.cache = Module._cache;

    return require;
}

module.exports = transpiledRequire;