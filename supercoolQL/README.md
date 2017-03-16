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

  if (testQuestion1 && testQuestion2 || testQuestion3) {
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
- [x] QLVisitor implementation
- [x] Replacement error listener for parser
- [x] Application launcher
- [x] AST
  - [x] Form, Statement, Question classes
  - [x] IfStatement, IfElseStatement classes
  - [x] Classes for Operations
  - [x] Basic types
    - [x] Strings
    - [x] Booleans
    - [x] Integers
- [ ] AST Processing
  - [ ] Type checking
    - [x] reference to undefined questions
    - [x] duplicate question declarations with different types
    - [x] conditions that are not of the type boolean
    - [x] operands of invalid type to operators
    - [ ] cyclic dependencies between questions
    - [x] duplicate labels
  - [x] Error handling
    - [x] Error class
    - [x] Error collection class
    - [x] Make all tests return Error objects
    - [x] Halt on bad error - don't throw exception!
    - [x] Output in GUI
- [ ] GUI generation
- [x] Unit tests

# ToDo

- [x] Tests for various parsing stages
- [ ] Split QLVisitor Interface into smaller interfaces
- [ ] Remove AbstractQLVisitor class
- [x] Create Runnable GUI class for Main()
    - [x] Move GUI logic out of FileReader
- [ ] Finish adding line/column metadata to AST nodes
    - [ ] Clean up line/column inheritance from TreeNode
    
