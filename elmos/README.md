# Elmos

<img src="https://upload.wikimedia.org/wikipedia/en/7/74/Elmo_from_Sesame_Street.gif" alt="Elmo_from_Sesame_Street.gif"/>

Authors:
* [Paco van Beckhoven](https://github.com/pacbeckh)
* [Mats Stijlaart](https://github.com/stil4m)

## Prerequisites

* Node `>= 6.X.X`
* Elm `0.18`


## Setup

```
npm install
```

## Tests

```
./node_modules/.bin/elm-test
```

## Guidelines

* Modules are build from big to small (upper functions use lower defined functions if possible).
* Tests for a module are located in the `tests/<MODULE_PATH>Tests.elm`.
