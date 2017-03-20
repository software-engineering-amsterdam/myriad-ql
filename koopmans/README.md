### Team Koopmans:
* Jetse Koopmans (10292829)
* Arend Koopmans (10275673)

### Getting started
QL is tested on ruby '2.3.0'. Please run `bundle install` before you continue.

### How do I run QL?
```ruby
$ bundle exec ruby main.rb
```

### How do I run QLS?
Not yet...

### How do I run the test suite?
```ruby
$ bundle exec rspec
```
### Which style is used?
Rubocop, see https://github.com/bbatsov/rubocop

### How are errors handled?
Via the Constructivist style, see pp. 155-159 of Exercises in programming style by Cristina Videira Lopes.
 
### Example QL
```
form taxOfficeExample { 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
    
  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money = 
        (sellingPrice - privateDebt)
  }
 
}
```

### Example QLS
```
stylesheet taxOfficeExample 
  page Housing {
    section "Buying"
      question hasBoughtHouse  
        widget checkbox 
    section "Loaning"  
      question hasMaintLoan
  }

  page Selling { 
    section "Selling" {
      question hasSoldHouse
        widget radio("Yes", "No") 
      section "You sold a house" {
        question sellingPrice
          widget spinbox
        question privateDebt
          widget spinbox 
        question valueResidue
        default money {
          width: 400
          font: "Arial" 
          fontsize: 14
          color: #999999
          widget spinbox
        }        
      }
    }
    default boolean widget radio("Yes", "No")
  }
```

precedence eval van expressions

ql parser
integer negation  is type checking dus hoeft hier niet
+ regex en repeat(1)

base visitor -> flat map 
tee veel doen
flatten?
heeft niet zo veel zin

question visitor naam klopt niet -> question collector


type checker
duplicate label checker
class met maar 1 method misschien naar groter geheel?

cyclic checker
values -> variables
visit_variable: split functie in visit_variable en cycles
check cycles

notification -> error , warning

operand type
types een parameter ipv visite
visit variable returned direct de variable
undefined ? -> error
a + b, b is undefined dan error voor b undefined en niet error van a+b
push return
push -> <<
unless hier beetje onduidelijk
class meegeven?
.class hoort bij presentatie

undefined variable
waarom niet hele variable in ding
moet je wel ff equals in variable defileren


BooleanType -> BooleanType.new


accept types tricky? hoe weten we nou wat met wat mag berekenen worden
accepted_types
welke combinaties
naar de type checker verplaatsen


GUI

variable pas opzoeken 

If statement
condition

question condition
is geen ast gedoe


ast
computed question

