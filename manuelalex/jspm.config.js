SystemJS.config({
    paths: {
        "npm:": "jspm_packages/npm/",
        "src/": "src/",
        "github:": "jspm_packages/github/",
        "manuelalex/": "src/"
    },
    arvaOptions: {
        "iconOptions": {
            "form": "rounded",
            "thickness": "bold"
        }
    },
    babelOptions: {
        "plugins": [
            "babel-plugin-transform-decorators-legacy",
            "babel-plugin-transform-class-properties",
            [
                "babel-plugin-transform-builtin-extend",
                {
                    "globals": [
                        "Array"
                    ],
                    "approximate": true
                }
            ],
            [
                "babel-plugin-transform-es2015-spread",
                {
                    "loose": true
                }
            ],
            [
                "babel-plugin-transform-es2015-for-of",
                {
                    "loose": true
                }
            ]
        ]
    },
    browserConfig: {
        "baseURL": "/"
    },
    devConfig: {
        "map": {
            "plugin-babel": "npm:systemjs-plugin-babel@0.0.20",
            "babel-plugin-transform-decorators-legacy": "npm:babel-plugin-transform-decorators-legacy@1.3.4",
            "babel-plugin-transform-class-properties": "npm:babel-plugin-transform-class-properties@6.22.0",
            "babel-plugin-transform-builtin-extend": "npm:babel-plugin-transform-builtin-extend@1.1.2",
            "babel-plugin-transform-es2015-spread": "npm:babel-plugin-transform-es2015-spread@6.22.0",
            "babel-plugin-transform-es2015-for-of": "npm:babel-plugin-transform-es2015-for-of@6.22.0"
        },
        "packages": {
            "npm:babel-plugin-transform-decorators-legacy@1.3.4": {
                "map": {
                    "babel-runtime": "npm:babel-runtime@6.22.0",
                    "babel-template": "npm:babel-template@6.22.0",
                    "babel-plugin-syntax-decorators": "npm:babel-plugin-syntax-decorators@6.13.0"
                }
            },
            "npm:babel-plugin-transform-class-properties@6.22.0": {
                "map": {
                    "babel-runtime": "npm:babel-runtime@6.22.0",
                    "babel-template": "npm:babel-template@6.22.0",
                    "babel-helper-function-name": "npm:babel-helper-function-name@6.22.0",
                    "babel-plugin-syntax-class-properties": "npm:babel-plugin-syntax-class-properties@6.13.0"
                }
            },
            "npm:babel-helper-function-name@6.22.0": {
                "map": {
                    "babel-runtime": "npm:babel-runtime@6.22.0",
                    "babel-template": "npm:babel-template@6.22.0",
                    "babel-helper-get-function-arity": "npm:babel-helper-get-function-arity@6.22.0",
                    "babel-types": "npm:babel-types@6.22.0",
                    "babel-traverse": "npm:babel-traverse@6.22.1"
                }
            },
            "npm:babel-helper-get-function-arity@6.22.0": {
                "map": {
                    "babel-runtime": "npm:babel-runtime@6.22.0",
                    "babel-types": "npm:babel-types@6.22.0"
                }
            },
            "npm:babel-plugin-transform-builtin-extend@1.1.2": {
                "map": {
                    "babel-template": "npm:babel-template@6.22.0",
                    "babel-runtime": "npm:babel-runtime@6.22.0"
                }
            },
            "npm:babel-plugin-transform-es2015-spread@6.22.0": {
                "map": {
                    "babel-runtime": "npm:babel-runtime@6.22.0"
                }
            },
            "npm:babel-plugin-transform-es2015-for-of@6.22.0": {
                "map": {
                    "babel-runtime": "npm:babel-runtime@6.22.0"
                }
            }
        }
    },
    transpiler: "plugin-babel",
    packages: {
        "manuelalex": {
            "main": "main.js",
            "format": "esm",
            "meta": {
                "*.js": {
                    "loader": "plugin-babel"
                }
            }
        }
    },
    map: {
        "text": "github:bizboard/arva-js@develop/utils/IconLoader.js"
    }
});

SystemJS.config({
    packageConfigPaths: [
        "npm:@*/*.json",
        "npm:*.json",
        "github:*/*.json"
    ],
    map: {
        "css": "github:systemjs/plugin-css@0.1.32",
        "arva-js": "github:manueloverdijk/arva-js@develop",
        "arva-kit": "github:manueloverdijk/arva-kit@develop",
        "assert": "npm:jspm-nodelibs-assert@0.2.0",
        "babel-register": "npm:babel-register@6.22.0",
        "buffer": "npm:jspm-nodelibs-buffer@0.2.1",
        "child_process": "npm:jspm-nodelibs-child_process@0.2.0",
        "constants": "npm:jspm-nodelibs-constants@0.2.0",
        "crypto": "npm:jspm-nodelibs-crypto@0.2.0",
        "domain": "npm:jspm-nodelibs-domain@0.2.0",
        "events": "npm:jspm-nodelibs-events@0.2.0",
        "famous": "github:bizboard/famous@develop",
        "fs": "npm:jspm-nodelibs-fs@0.2.0",
        "http": "npm:jspm-nodelibs-http@0.2.0",
        "https": "npm:jspm-nodelibs-https@0.2.1",
        "lodash": "npm:lodash@4.17.4",
        "module": "npm:jspm-nodelibs-module@0.2.0",
        "nearley": "npm:nearley@2.7.12",
        "nearly": "npm:nearly@1.7.1",
        "os": "npm:jspm-nodelibs-os@0.2.0",
        "path": "npm:jspm-nodelibs-path@0.2.1",
        "process": "npm:jspm-nodelibs-process@0.2.0",
        "react": "npm:react@15.4.2",
        "readline": "npm:jspm-nodelibs-readline@0.2.0",
        "source-map": "npm:source-map@0.5.6",
        "stream": "npm:jspm-nodelibs-stream@0.2.0",
        "string_decoder": "npm:jspm-nodelibs-string_decoder@0.2.0",
        "timers": "npm:jspm-nodelibs-timers@0.2.0",
        "url": "npm:jspm-nodelibs-url@0.2.0",
        "util": "npm:jspm-nodelibs-util@0.2.1",
        "vm": "npm:jspm-nodelibs-vm@0.2.0",
        "zlib": "npm:jspm-nodelibs-zlib@0.2.2"
    },
    packages: {
        "npm:jspm-nodelibs-buffer@0.2.1": {
            "map": {
                "buffer": "npm:buffer@4.9.1"
            }
        },
        "npm:buffer@4.9.1": {
            "map": {
                "isarray": "npm:isarray@1.0.0",
                "base64-js": "npm:base64-js@1.2.0",
                "ieee754": "npm:ieee754@1.1.8"
            }
        },
        "npm:jspm-nodelibs-os@0.2.0": {
            "map": {
                "os-browserify": "npm:os-browserify@0.2.1"
            }
        },
        "npm:jspm-nodelibs-crypto@0.2.0": {
            "map": {
                "crypto-browserify": "npm:crypto-browserify@3.11.0"
            }
        },
        "npm:crypto-browserify@3.11.0": {
            "map": {
                "inherits": "npm:inherits@2.0.3",
                "browserify-sign": "npm:browserify-sign@4.0.0",
                "browserify-cipher": "npm:browserify-cipher@1.0.0",
                "randombytes": "npm:randombytes@2.0.3",
                "create-ecdh": "npm:create-ecdh@4.0.0",
                "create-hmac": "npm:create-hmac@1.1.4",
                "create-hash": "npm:create-hash@1.1.2",
                "pbkdf2": "npm:pbkdf2@3.0.9",
                "public-encrypt": "npm:public-encrypt@4.0.0",
                "diffie-hellman": "npm:diffie-hellman@5.0.2"
            }
        },
        "npm:browserify-sign@4.0.0": {
            "map": {
                "create-hmac": "npm:create-hmac@1.1.4",
                "inherits": "npm:inherits@2.0.3",
                "create-hash": "npm:create-hash@1.1.2",
                "elliptic": "npm:elliptic@6.4.0",
                "bn.js": "npm:bn.js@4.11.6",
                "browserify-rsa": "npm:browserify-rsa@4.0.1",
                "parse-asn1": "npm:parse-asn1@5.0.0"
            }
        },
        "npm:create-hmac@1.1.4": {
            "map": {
                "inherits": "npm:inherits@2.0.3",
                "create-hash": "npm:create-hash@1.1.2"
            }
        },
        "npm:create-hash@1.1.2": {
            "map": {
                "inherits": "npm:inherits@2.0.3",
                "sha.js": "npm:sha.js@2.4.8",
                "cipher-base": "npm:cipher-base@1.0.3",
                "ripemd160": "npm:ripemd160@1.0.1"
            }
        },
        "npm:public-encrypt@4.0.0": {
            "map": {
                "create-hash": "npm:create-hash@1.1.2",
                "randombytes": "npm:randombytes@2.0.3",
                "bn.js": "npm:bn.js@4.11.6",
                "browserify-rsa": "npm:browserify-rsa@4.0.1",
                "parse-asn1": "npm:parse-asn1@5.0.0"
            }
        },
        "npm:diffie-hellman@5.0.2": {
            "map": {
                "randombytes": "npm:randombytes@2.0.3",
                "bn.js": "npm:bn.js@4.11.6",
                "miller-rabin": "npm:miller-rabin@4.0.0"
            }
        },
        "npm:pbkdf2@3.0.9": {
            "map": {
                "create-hmac": "npm:create-hmac@1.1.4"
            }
        },
        "npm:browserify-cipher@1.0.0": {
            "map": {
                "evp_bytestokey": "npm:evp_bytestokey@1.0.0",
                "browserify-des": "npm:browserify-des@1.0.0",
                "browserify-aes": "npm:browserify-aes@1.0.6"
            }
        },
        "npm:create-ecdh@4.0.0": {
            "map": {
                "elliptic": "npm:elliptic@6.4.0",
                "bn.js": "npm:bn.js@4.11.6"
            }
        },
        "npm:evp_bytestokey@1.0.0": {
            "map": {
                "create-hash": "npm:create-hash@1.1.2"
            }
        },
        "npm:browserify-des@1.0.0": {
            "map": {
                "inherits": "npm:inherits@2.0.3",
                "cipher-base": "npm:cipher-base@1.0.3",
                "des.js": "npm:des.js@1.0.0"
            }
        },
        "npm:browserify-rsa@4.0.1": {
            "map": {
                "randombytes": "npm:randombytes@2.0.3",
                "bn.js": "npm:bn.js@4.11.6"
            }
        },
        "npm:browserify-aes@1.0.6": {
            "map": {
                "create-hash": "npm:create-hash@1.1.2",
                "evp_bytestokey": "npm:evp_bytestokey@1.0.0",
                "inherits": "npm:inherits@2.0.3",
                "cipher-base": "npm:cipher-base@1.0.3",
                "buffer-xor": "npm:buffer-xor@1.0.3"
            }
        },
        "npm:parse-asn1@5.0.0": {
            "map": {
                "browserify-aes": "npm:browserify-aes@1.0.6",
                "create-hash": "npm:create-hash@1.1.2",
                "pbkdf2": "npm:pbkdf2@3.0.9",
                "evp_bytestokey": "npm:evp_bytestokey@1.0.0",
                "asn1.js": "npm:asn1.js@4.9.1"
            }
        },
        "npm:cipher-base@1.0.3": {
            "map": {
                "inherits": "npm:inherits@2.0.3"
            }
        },
        "npm:sha.js@2.4.8": {
            "map": {
                "inherits": "npm:inherits@2.0.3"
            }
        },
        "npm:miller-rabin@4.0.0": {
            "map": {
                "bn.js": "npm:bn.js@4.11.6",
                "brorand": "npm:brorand@1.1.0"
            }
        },
        "npm:jspm-nodelibs-stream@0.2.0": {
            "map": {
                "stream-browserify": "npm:stream-browserify@2.0.1"
            }
        },
        "npm:hash.js@1.0.3": {
            "map": {
                "inherits": "npm:inherits@2.0.3"
            }
        },
        "npm:des.js@1.0.0": {
            "map": {
                "inherits": "npm:inherits@2.0.3",
                "minimalistic-assert": "npm:minimalistic-assert@1.0.0"
            }
        },
        "npm:stream-browserify@2.0.1": {
            "map": {
                "inherits": "npm:inherits@2.0.3",
                "readable-stream": "npm:readable-stream@2.2.3"
            }
        },
        "npm:asn1.js@4.9.1": {
            "map": {
                "bn.js": "npm:bn.js@4.11.6",
                "inherits": "npm:inherits@2.0.3",
                "minimalistic-assert": "npm:minimalistic-assert@1.0.0"
            }
        },
        "npm:readable-stream@2.2.2": {
            "map": {
                "isarray": "npm:isarray@1.0.0",
                "inherits": "npm:inherits@2.0.3",
                "string_decoder": "npm:string_decoder@0.10.31",
                "buffer-shims": "npm:buffer-shims@1.0.0",
                "core-util-is": "npm:core-util-is@1.0.2",
                "process-nextick-args": "npm:process-nextick-args@1.0.7",
                "util-deprecate": "npm:util-deprecate@1.0.2"
            }
        },
        "npm:jspm-nodelibs-string_decoder@0.2.0": {
            "map": {
                "string_decoder-browserify": "npm:string_decoder@0.10.31"
            }
        },
        "npm:babel-register@6.22.0": {
            "map": {
                "babel-runtime": "npm:babel-runtime@6.22.0",
                "core-js": "npm:core-js@2.4.1",
                "home-or-tmp": "npm:home-or-tmp@2.0.0",
                "mkdirp": "npm:mkdirp@0.5.1",
                "source-map-support": "npm:source-map-support@0.4.11",
                "lodash": "npm:lodash@4.17.4",
                "babel-core": "npm:babel-core@6.22.1"
            }
        },
        "npm:babel-runtime@6.22.0": {
            "map": {
                "core-js": "npm:core-js@2.4.1",
                "regenerator-runtime": "npm:regenerator-runtime@0.10.1"
            }
        },
        "npm:home-or-tmp@2.0.0": {
            "map": {
                "os-homedir": "npm:os-homedir@1.0.2",
                "os-tmpdir": "npm:os-tmpdir@1.0.2"
            }
        },
        "npm:source-map-support@0.4.11": {
            "map": {
                "source-map": "npm:source-map@0.5.6"
            }
        },
        "npm:mkdirp@0.5.1": {
            "map": {
                "minimist": "npm:minimist@0.0.8"
            }
        },
        "npm:babel-core@6.22.1": {
            "map": {
                "lodash": "npm:lodash@4.17.4",
                "source-map": "npm:source-map@0.5.6",
                "babel-runtime": "npm:babel-runtime@6.22.0",
                "babel-register": "npm:babel-register@6.22.0",
                "babel-code-frame": "npm:babel-code-frame@6.22.0",
                "babel-template": "npm:babel-template@6.22.0",
                "babel-traverse": "npm:babel-traverse@6.22.1",
                "babel-messages": "npm:babel-messages@6.22.0",
                "convert-source-map": "npm:convert-source-map@1.3.0",
                "babel-types": "npm:babel-types@6.22.0",
                "path-is-absolute": "npm:path-is-absolute@1.0.1",
                "babylon": "npm:babylon@6.15.0",
                "private": "npm:private@0.1.7",
                "slash": "npm:slash@1.0.0",
                "json5": "npm:json5@0.5.1",
                "babel-helpers": "npm:babel-helpers@6.22.0",
                "babel-generator": "npm:babel-generator@6.22.0",
                "minimatch": "npm:minimatch@3.0.3",
                "debug": "npm:debug@2.6.0"
            }
        },
        "npm:babel-traverse@6.22.1": {
            "map": {
                "lodash": "npm:lodash@4.17.4",
                "babel-code-frame": "npm:babel-code-frame@6.22.0",
                "babel-messages": "npm:babel-messages@6.22.0",
                "babel-runtime": "npm:babel-runtime@6.22.0",
                "babel-types": "npm:babel-types@6.22.0",
                "babylon": "npm:babylon@6.15.0",
                "globals": "npm:globals@9.14.0",
                "invariant": "npm:invariant@2.2.2",
                "debug": "npm:debug@2.6.1"
            }
        },
        "npm:babel-template@6.22.0": {
            "map": {
                "lodash": "npm:lodash@4.17.4",
                "babylon": "npm:babylon@6.15.0",
                "babel-traverse": "npm:babel-traverse@6.22.1",
                "babel-types": "npm:babel-types@6.22.0",
                "babel-runtime": "npm:babel-runtime@6.22.0"
            }
        },
        "npm:babel-types@6.22.0": {
            "map": {
                "lodash": "npm:lodash@4.17.4",
                "babel-runtime": "npm:babel-runtime@6.22.0",
                "esutils": "npm:esutils@2.0.2",
                "to-fast-properties": "npm:to-fast-properties@1.0.2"
            }
        },
        "npm:babel-messages@6.22.0": {
            "map": {
                "babel-runtime": "npm:babel-runtime@6.22.0"
            }
        },
        "npm:babel-code-frame@6.22.0": {
            "map": {
                "esutils": "npm:esutils@2.0.2",
                "js-tokens": "npm:js-tokens@3.0.1",
                "chalk": "npm:chalk@1.1.3"
            }
        },
        "npm:babel-helpers@6.22.0": {
            "map": {
                "babel-runtime": "npm:babel-runtime@6.22.0",
                "babel-template": "npm:babel-template@6.22.0"
            }
        },
        "npm:babel-generator@6.22.0": {
            "map": {
                "lodash": "npm:lodash@4.17.4",
                "source-map": "npm:source-map@0.5.6",
                "babel-messages": "npm:babel-messages@6.22.0",
                "babel-runtime": "npm:babel-runtime@6.22.0",
                "babel-types": "npm:babel-types@6.22.0",
                "detect-indent": "npm:detect-indent@4.0.0",
                "jsesc": "npm:jsesc@1.3.0"
            }
        },
        "npm:debug@2.6.0": {
            "map": {
                "ms": "npm:ms@0.7.2"
            }
        },
        "npm:invariant@2.2.2": {
            "map": {
                "loose-envify": "npm:loose-envify@1.3.1"
            }
        },
        "npm:loose-envify@1.3.1": {
            "map": {
                "js-tokens": "npm:js-tokens@3.0.1"
            }
        },
        "npm:minimatch@3.0.3": {
            "map": {
                "brace-expansion": "npm:brace-expansion@1.1.6"
            }
        },
        "npm:chalk@1.1.3": {
            "map": {
                "ansi-styles": "npm:ansi-styles@2.2.1",
                "strip-ansi": "npm:strip-ansi@3.0.1",
                "has-ansi": "npm:has-ansi@2.0.0",
                "escape-string-regexp": "npm:escape-string-regexp@1.0.5",
                "supports-color": "npm:supports-color@2.0.0"
            }
        },
        "npm:detect-indent@4.0.0": {
            "map": {
                "repeating": "npm:repeating@2.0.1"
            }
        },
        "npm:brace-expansion@1.1.6": {
            "map": {
                "balanced-match": "npm:balanced-match@0.4.2",
                "concat-map": "npm:concat-map@0.0.1"
            }
        },
        "npm:has-ansi@2.0.0": {
            "map": {
                "ansi-regex": "npm:ansi-regex@2.1.1"
            }
        },
        "npm:strip-ansi@3.0.1": {
            "map": {
                "ansi-regex": "npm:ansi-regex@2.1.1"
            }
        },
        "npm:repeating@2.0.1": {
            "map": {
                "is-finite": "npm:is-finite@1.0.2"
            }
        },
        "npm:is-finite@1.0.2": {
            "map": {
                "number-is-nan": "npm:number-is-nan@1.0.1"
            }
        },
        "npm:react@15.4.2": {
            "map": {
                "object-assign": "npm:object-assign@4.1.1",
                "loose-envify": "npm:loose-envify@1.3.1",
                "fbjs": "npm:fbjs@0.8.9"
            }
        },
        "npm:fbjs@0.8.9": {
            "map": {
                "loose-envify": "npm:loose-envify@1.3.1",
                "object-assign": "npm:object-assign@4.1.1",
                "setimmediate": "npm:setimmediate@1.0.5",
                "core-js": "npm:core-js@1.2.7",
                "promise": "npm:promise@7.1.1",
                "isomorphic-fetch": "npm:isomorphic-fetch@2.2.1",
                "ua-parser-js": "npm:ua-parser-js@0.7.12"
            }
        },
        "npm:promise@7.1.1": {
            "map": {
                "asap": "npm:asap@2.0.5"
            }
        },
        "npm:isomorphic-fetch@2.2.1": {
            "map": {
                "whatwg-fetch": "npm:whatwg-fetch@2.0.2",
                "node-fetch": "npm:node-fetch@1.6.3"
            }
        },
        "npm:node-fetch@1.6.3": {
            "map": {
                "is-stream": "npm:is-stream@1.1.0",
                "encoding": "npm:encoding@0.1.12"
            }
        },
        "npm:encoding@0.1.12": {
            "map": {
                "iconv-lite": "npm:iconv-lite@0.4.15"
            }
        },
        "npm:jspm-nodelibs-domain@0.2.0": {
            "map": {
                "domain-browserify": "npm:domain-browser@1.1.7"
            }
        },
        "npm:jspm-nodelibs-http@0.2.0": {
            "map": {
                "http-browserify": "npm:stream-http@2.6.3"
            }
        },
        "npm:stream-http@2.6.3": {
            "map": {
                "inherits": "npm:inherits@2.0.3",
                "readable-stream": "npm:readable-stream@2.2.2",
                "xtend": "npm:xtend@4.0.1",
                "builtin-status-codes": "npm:builtin-status-codes@3.0.0",
                "to-arraybuffer": "npm:to-arraybuffer@1.0.1"
            }
        },
        "npm:jspm-nodelibs-zlib@0.2.2": {
            "map": {
                "browserify-zlib": "npm:browserify-zlib@0.1.4"
            }
        },
        "npm:jspm-nodelibs-url@0.2.0": {
            "map": {
                "url-browserify": "npm:url@0.11.0"
            }
        },
        "npm:browserify-zlib@0.1.4": {
            "map": {
                "readable-stream": "npm:readable-stream@2.2.2",
                "pako": "npm:pako@0.2.9"
            }
        },
        "npm:url@0.11.0": {
            "map": {
                "punycode": "npm:punycode@1.3.2",
                "querystring": "npm:querystring@0.2.0"
            }
        },
        "npm:debug@2.6.1": {
            "map": {
                "ms": "npm:ms@0.7.2"
            }
        },
        "npm:nearley@2.7.12": {
            "map": {
                "randexp": "npm:randexp@0.4.4",
                "railroad-diagrams": "npm:railroad-diagrams@1.0.0",
                "nomnom": "npm:nomnom@1.6.2"
            }
        },
        "npm:randexp@0.4.4": {
            "map": {
                "discontinuous-range": "npm:discontinuous-range@1.0.0",
                "ret": "npm:ret@0.1.13"
            }
        },
        "npm:nomnom@1.6.2": {
            "map": {
                "underscore": "npm:underscore@1.4.4",
                "colors": "npm:colors@0.5.1"
            }
        },
        "npm:readable-stream@2.2.3": {
            "map": {
                "isarray": "npm:isarray@1.0.0",
                "inherits": "npm:inherits@2.0.3",
                "string_decoder": "npm:string_decoder@0.10.31",
                "core-util-is": "npm:core-util-is@1.0.2",
                "process-nextick-args": "npm:process-nextick-args@1.0.7",
                "buffer-shims": "npm:buffer-shims@1.0.0",
                "util-deprecate": "npm:util-deprecate@1.0.2"
            }
        },
        "npm:elliptic@6.4.0": {
            "map": {
                "bn.js": "npm:bn.js@4.11.6",
                "inherits": "npm:inherits@2.0.3",
                "minimalistic-assert": "npm:minimalistic-assert@1.0.0",
                "hash.js": "npm:hash.js@1.0.3",
                "brorand": "npm:brorand@1.1.0",
                "hmac-drbg": "npm:hmac-drbg@1.0.0",
                "minimalistic-crypto-utils": "npm:minimalistic-crypto-utils@1.0.1"
            }
        },
        "npm:hmac-drbg@1.0.0": {
            "map": {
                "hash.js": "npm:hash.js@1.0.3",
                "minimalistic-crypto-utils": "npm:minimalistic-crypto-utils@1.0.1",
                "minimalistic-assert": "npm:minimalistic-assert@1.0.0"
            }
        },
        "npm:jspm-nodelibs-timers@0.2.0": {
            "map": {
                "timers-browserify": "npm:timers-browserify@1.4.2"
            }
        },
        "npm:timers-browserify@1.4.2": {
            "map": {
                "process": "npm:process@0.11.9"
            }
        },
        "github:manueloverdijk/arva-js@develop": {
            "map": {
                "bowser": "npm:bowser@1.6.0",
                "xml2js": "npm:xml2js@0.4.17",
                "fastclick": "npm:fastclick@1.0.6",
                "camelcase": "npm:camelcase@2.1.1",
                "lodash-decorators": "npm:lodash-decorators@3.0.1",
                "ordered-hashmap": "npm:ordered-hashmap@1.0.0",
                "eventemitter3": "npm:eventemitter3@1.2.0",
                "lodash": "npm:lodash@4.17.4",
                "famous-flex": "github:bizboard/famous-flex@master",
                "famous": "github:bizboard/famous@develop",
                "request-animation-frame-mock": "github:erykpiast/request-animation-frame-mock@0.1.8",
                "firebase": "github:firebase/firebase-bower@3.6.10"
            }
        },
        "npm:lodash-decorators@3.0.1": {
            "map": {
                "lodash": "npm:lodash@4.0.1"
            }
        },
        "npm:xml2js@0.4.17": {
            "map": {
                "xmlbuilder": "npm:xmlbuilder@4.2.1",
                "sax": "npm:sax@1.2.2"
            }
        },
        "npm:xmlbuilder@4.2.1": {
            "map": {
                "lodash": "npm:lodash@4.17.4"
            }
        },
        "github:bizboard/famous-flex@master": {
            "map": {
                "es6-map": "npm:es6-map@0.1.4"
            }
        },
        "npm:es6-map@0.1.4": {
            "map": {
                "es6-iterator": "npm:es6-iterator@2.0.0",
                "es6-set": "npm:es6-set@0.1.4",
                "es6-symbol": "npm:es6-symbol@3.1.0",
                "d": "npm:d@0.1.1",
                "es5-ext": "npm:es5-ext@0.10.12",
                "event-emitter": "npm:event-emitter@0.3.4"
            }
        },
        "npm:es6-iterator@2.0.0": {
            "map": {
                "d": "npm:d@0.1.1",
                "es5-ext": "npm:es5-ext@0.10.12",
                "es6-symbol": "npm:es6-symbol@3.1.0"
            }
        },
        "npm:es6-set@0.1.4": {
            "map": {
                "es6-iterator": "npm:es6-iterator@2.0.0",
                "d": "npm:d@0.1.1",
                "es5-ext": "npm:es5-ext@0.10.12",
                "es6-symbol": "npm:es6-symbol@3.1.0",
                "event-emitter": "npm:event-emitter@0.3.4"
            }
        },
        "npm:es6-symbol@3.1.0": {
            "map": {
                "d": "npm:d@0.1.1",
                "es5-ext": "npm:es5-ext@0.10.12"
            }
        },
        "npm:d@0.1.1": {
            "map": {
                "es5-ext": "npm:es5-ext@0.10.12"
            }
        },
        "npm:event-emitter@0.3.4": {
            "map": {
                "es5-ext": "npm:es5-ext@0.10.12",
                "d": "npm:d@0.1.1"
            }
        },
        "npm:es5-ext@0.10.12": {
            "map": {
                "es6-symbol": "npm:es6-symbol@3.1.0",
                "es6-iterator": "npm:es6-iterator@2.0.0"
            }
        },
        "github:manueloverdijk/arva-kit@develop": {
            "map": {
                "color": "npm:color@0.11.4",
                "bowser": "npm:bowser@1.4.3",
                "lodash": "npm:lodash@4.17.4",
                "degrees-radians": "npm:degrees-radians@1.0.3",
                "rgbcolor": "npm:rgbcolor@0.0.4",
                "famous-autosizetextarea": "github:ijzerenhein/famous-autosizetextarea@0.3.1",
                "arva-js": "github:bizboard/arva-js@develop",
                "famous-bkimagesurface": "github:bizboard/famous-bkimagesurface@master",
                "famous-flex": "github:bizboard/famous-flex@master",
                "famous": "github:bizboard/famous@develop"
            }
        },
        "npm:color@0.11.4": {
            "map": {
                "clone": "npm:clone@1.0.2",
                "color-convert": "npm:color-convert@1.9.0",
                "color-string": "npm:color-string@0.3.0"
            }
        },
        "npm:color-convert@1.9.0": {
            "map": {
                "color-name": "npm:color-name@1.1.1"
            }
        },
        "npm:color-string@0.3.0": {
            "map": {
                "color-name": "npm:color-name@1.1.1"
            }
        },
        "github:bizboard/arva-js@develop": {
            "map": {
                "bowser": "npm:bowser@1.6.0",
                "lodash": "npm:lodash@4.17.4",
                "famous": "github:bizboard/famous@develop",
                "famous-flex": "github:bizboard/famous-flex@master",
                "camelcase": "npm:camelcase@2.1.1",
                "xml2js": "npm:xml2js@0.4.17",
                "eventemitter3": "npm:eventemitter3@1.2.0",
                "lodash-decorators": "npm:lodash-decorators@3.0.1",
                "fastclick": "npm:fastclick@1.0.6",
                "ordered-hashmap": "npm:ordered-hashmap@1.0.0",
                "request-animation-frame-mock": "github:erykpiast/request-animation-frame-mock@0.1.8",
                "firebase": "github:firebase/firebase-bower@3.6.10"
            }
        }
    }
});
