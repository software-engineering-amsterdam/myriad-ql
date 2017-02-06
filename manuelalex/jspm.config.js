SystemJS.config({
  paths: {
    "npm:": "jspm_packages/npm/",
    "src/": "src/"
  },
  browserConfig: {
    "baseURL": "/"
  },
  devConfig: {
    "map": {
      "plugin-babel": "npm:systemjs-plugin-babel@0.0.20"
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
  }
});

SystemJS.config({
  packageConfigPaths: [
    "npm:@*/*.json",
    "npm:*.json"
  ],
  map: {},
  packages: {}
});
