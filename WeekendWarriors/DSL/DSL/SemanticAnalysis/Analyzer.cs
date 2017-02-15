using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.AST;
using DSL.AST.Operators;

namespace DSL.SemanticAnalysis
{
    class Analyzer
    {
        private Dictionary<string, QLType> IdentifiertoType;

        public Analyzer()
        {
            IdentifiertoType = new Dictionary<string, QLType>();
        }

        public void Analyze(AST.INode node)
        {
            Visit((dynamic)node);
        } 
        
        protected QLType Visit(QLForm node)
        {
            Console.WriteLine("Form " + node.Identifier);

            foreach (var statement in node.Statements)
                Visit((dynamic)statement);

            return QLType.None;
        }

        protected QLType Visit(QLQuestion node)
        {
            Console.WriteLine("Question " + node.Body);
            // Store the type of this identifier
            IdentifiertoType[node.Identifier] = node.Type;

            return QLType.None;
        }

        protected QLType Visit(QLComputedQuestion node)
        {
            Console.WriteLine("Computed question");

            QLType assigneeType = Visit((dynamic)node.Question);
            QLType assignorType = Visit((dynamic)node.Expression);

            // TODO: improve
            if(assigneeType != assignorType)
                OnSemanticError(new SemanticErrorArgs("Assigning value of type <X> to variable of type <y> is not allowed"));

            return QLType.None;
        }

        protected QLType Visit(QLConditional node)
        {
            Console.WriteLine("Conditional");
            Visit((dynamic)node.Condition);
            foreach (var statement in node.ThenStatements)
                Visit((dynamic)statement);
            foreach (var statement in node.ElseStatements)
                Visit((dynamic)statement);

            return QLType.None;
        }

        protected QLType Visit(QLArithmaticOperation node)
        {          
            Console.WriteLine("Arithmatic operation");
            QLType lhsType = Visit((dynamic)node.Lhs);
            QLType rhsType = Visit((dynamic)node.Rhs);

            // TODO: Do this for real using a proper object
            if (lhsType == rhsType)
                return lhsType;
            else
                return QLType.None;
        }

        protected QLType Visit(QLComparisonOperator node)
        {
            Console.WriteLine("Comparison operation");
            Visit((dynamic)node.Lhs);
            Visit((dynamic)node.Rhs);

            return QLType.Bool;
        }

        protected QLType Visit(QLEqualityOperator node)
        {
            Console.WriteLine("Equality operation");
            Visit((dynamic)node.Lhs);
            Visit((dynamic)node.Rhs);

            return QLType.Bool;
        }

        protected QLType Visit(QLUnaryOperator node)
        {
            Console.WriteLine("Unary operation");
            QLType operandType = Visit((dynamic)node.Operand);

            return operandType;
        }

        protected QLType Visit(QLBoolean node)
        {
            Console.WriteLine("Boolean");

            return QLType.Bool;
        }
            
        protected QLType Visit(QLMoney node)
        {
            Console.WriteLine("Money");

            return QLType.Money;
        }   

        protected QLType Visit(QLNumber node)
        {
            Console.WriteLine("Number");

            return QLType.Number;
        }

        protected QLType Visit(QLString node)
        {
            Console.WriteLine("String");

            return QLType.String;
        }

        protected QLType Visit(QLIdentifier node)
        {
            Console.WriteLine("Identifier");

            if (IdentifiertoType.ContainsKey(node.Name))
            {
                return IdentifiertoType[node.Name];
            }
            else
            {
                /* TODO: double check if there is any way in which we can get here
                 * before we get to the question node that introduces the identifier.
                 * Since it is not specified we will take the old school/easy way and
                 * only allow usage of a variable after declaration */

                OnSemanticError(new SemanticErrorArgs("Encountered undefined Identifier \"" + node.Name));
            }

            // TODO Does this make sense? Do we want to go on parsing after an error?
            return QLType.None;
        }

        public delegate void SemanticErrorEventHandler(object sender, SemanticErrorArgs e);
        public event SemanticErrorEventHandler SemanticError;

        protected virtual void OnSemanticError(SemanticErrorArgs e)
        {
            if (SemanticError != null)
                SemanticError(this, e);
        }

    }
}
