package sc.ql.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;


import sc.ql.antlr.QLLexer;
import sc.ql.antlr.QLParser;
import sc.ql.ast.AstVisitor;
import sc.ql.checkform.CheckForm;
import sc.ql.model.Atom;
import sc.ql.model.Form;
import sc.ql.model.form_elements.Question;
import sc.ql.model.Expressions.Expression;

public class ModelTest {

	@Test
	public void FormTest() throws Exception {
		
		String oneQuestionForm = "form \"dit is een test\" buyingPrice MONEY endform";
		StringReader input = new StringReader(oneQuestionForm);
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));

        QLParser.FormContext context = parser.form();
	}
	
	@Test
	public void QuestionTest() throws Exception{
		//setup
		String vraag			= "This is a question";
		Atom<String> id 		= new Atom<String>(Atom.Type.ID, "buyingPrice");
		Atom.Type type 			= Atom.Type.MONEY;
		Expression expression	= (Expression) null;
		Integer getal			= 1;
		Question questoin = new Question(vraag, id, type, expression, getal);
		//excution
		Form form = getModel("form \"\" buyingPrice MONEY endform");

		//result
		//System.out.println("form info: "+ form.getFormElements());
       	//new CheckForm(form);
		
       	System.out.println(questoin.getLineNumber());
		Assert.assertEquals("This is a question", questoin.getQuestion());
		Assert.assertEquals("buyingPrice", questoin.getId().getValue());
		Assert.assertEquals(Atom.Type.MONEY, questoin.getType());
		Assert.assertEquals((Integer) 1, questoin.getLineNumber());
	}

		
	private Form getModel(String input) throws IOException{
		StringReader redstring = new StringReader(input);
        QLLexer lexer = new QLLexer(new ANTLRInputStream(redstring));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.form();
        
        AstVisitor visitor = new AstVisitor();
        Form form = (Form) visitor.visit(tree);
        
        return form;
	}
	
}
