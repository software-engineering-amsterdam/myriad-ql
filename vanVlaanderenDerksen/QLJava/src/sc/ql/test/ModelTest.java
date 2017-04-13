package sc.ql.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import sc.ql.model.Form;
import sc.ql.model.expressions.literals.IdLiteral;
import sc.ql.model.form_elements.Question;
import sc.ql.model.types.Type;
import sc.ql.antlr.QLLexer;
import sc.ql.antlr.QLParser;
import sc.ql.ast.AstVisitor;
import sc.ql.checkform.CheckForm;
import sc.ql.checkform.Message;
import sc.ql.checkform.QuestionTypeMap;

public class ModelTest {
	@Test
	public void testAssingment() throws Exception{
		
		String input = 
				"form test "
				+ "\"Did you sell a house in 2010?\" hasSoldHouse BOOLEAN "
				+ "\"Did you buy a house in 2010?\" hasBoughtHouse BOOLEAN "
				+ "\"Did you enter a loan?\" hasMaintLoan BOOLEAN "
					+ "if(hasSoldHouse) "
						+ "\"What was the selling price?\" sellingPrice MONEY "
						+ "\"Private debts for the sold house:\" privateDebt MONEY "
						+ "\"Value residue:\" valueResidue MONEY = (sellingPrice - privateDebt) "
						+ "endif "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();
    	   	
    	assertTrue(messages.isEmpty());
	}
	
	public Form createForm(String input){
		CharStream stream = new ANTLRInputStream(input);
        QLLexer lexer = new QLLexer((stream));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.form();
        
        AstVisitor visitor = new AstVisitor();
        return (Form) visitor.visit(tree);
	}

}
