# QL Implementation
**Author**: Matt Chapman

# Example QL File

```
form exampleForm {
  "Test question 1"
    testQuestion1: boolean;
  "Test question 2"
    testQuestion2: boolean;
  "Test question 3"
    testQuestion3: boolean;

  if (testQuestion1 AND testQuestion2 OR testQuestion3) {
    "Conditional question 1"
      conditionalQuestion1: integer;
    "Conditional question 2"
      conditionalQuestion2: integer;
    "Math question 1:"
      mathQuestion1:integer =
        (conditionalQuestion1 - conditionalQuestion2);
  }

   if (complexConditional) {
     "if Question"
         complexConditionalQuestion1: boolean;
   } else {
     "else Question"
        complexConditionalQuestion2: boolean;
   }
}
```

# Prerequisites

* Antlr4
* JUnit 4

# Usage

`java Launcher` to run. The application will provide a filepicker for input. Append the argument `-debug` to bypass the filepicker and run using `res/test.txt`.

# Implementation Progress
- [x] QL Grammar
- [ ] QLVisitor implementation
- [x] Replacement error listener for parser
- [x] Application launcher
- [ ] AST
  - [x] Form, Statement, Question classes
  - [x] IfStatement, IfElseStatement classes
  - [x] Classes for Operations
  - [x] Basic types
    - [x] Strings
    - [x] Booleans
    - [x] Integers
- [ ] AST Processing
  - [ ] Type checking
    - [ ] reference to undefined questions
    - [ ] duplicate question declarations with different types
    - [ ] conditions that are not of the type boolean
    - [ ] operands of invalid type to operators
    - [ ] cyclic dependencies between questions
    - [ ] duplicate labels
  - [ ] Error output in GUI
- [ ] GUI generation
- [ ] Unit tests
