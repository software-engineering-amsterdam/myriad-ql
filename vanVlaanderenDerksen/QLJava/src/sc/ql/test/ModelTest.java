package sc.ql.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;


import sc.ql.antlr.QLLexer;
import sc.ql.antlr.QLParser;
import sc.ql.ast.AstVisitor;
import sc.ql.checkform.CheckForm;
import sc.ql.checkform.QuestionsVisitor;
import sc.ql.model.atoms.AtomId;
import sc.ql.model.Form;
import sc.ql.model.Node;
import sc.ql.model.form_elements.Question;
import sc.ql.model.expressions.Expression;

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
	public void FormQuestionTest() throws Exception{
		//setup
		Form form = getModel("form \"This is a question\" buyingPrice MONEY endform");
		//excution
		List<Node> form_elements = form.getFormElements();
		List<Question> questions = new ArrayList<Question>();
		for (Node form_element : form_elements) {
			questions.addAll(form_element.accept(new QuestionsVisitor()));
        }
		//result
		//System.out.println("vraag: "+ questions.get(0).getQuestion());
		Assert.assertEquals("This is a question", questions.get(0).getQuestion());
		Assert.assertEquals("buyingPrice", questions.get(0).getId().getValue());
		Assert.assertEquals(Question.Type.MONEY, questions.get(0).getType());
		Assert.assertEquals((Integer) 1, questions.get(0).getLineNumber());
	}
	@Test
	public void SingleQuestionTest() throws Exception{
		String vraag			= "This is a question";
		AtomId id 				= new AtomId("buyingPrice", 0);
		Question.Type type 		= Question.Type.MONEY;
		Expression expression	= (Expression) null;
		Integer getal			= 1;
		Question questoin = new Question(vraag, id, type, expression, getal);
		
		
		Assert.assertEquals("This is a question", questoin.getQuestion());
		Assert.assertEquals("buyingPrice", questoin.getId().getValue());
		Assert.assertEquals(Question.Type.MONEY, questoin.getType());
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
