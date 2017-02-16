import importlib
import grammar.ql

def repl():
    running = True
    while(running):
        exp = raw_input("REPL>>")
        if (exp == 'exit'):
            running = False
        else:
            try:
                reload(grammar.ql)
                parser = grammar.ql.QL()
                parse_tree = parser.parse(exp)
                print(parse_tree.asXML())
            except Exception as e:
                print("Oops... bad input", e)



if __name__ == "__main__":
    repl()
