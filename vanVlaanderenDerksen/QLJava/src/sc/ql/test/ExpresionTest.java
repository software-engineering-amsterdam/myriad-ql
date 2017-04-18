package sc.ql.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import sc.ql.checkform.CheckForm;
import sc.ql.checkform.Message;
import sc.ql.model.Form;

public class ExpresionTest extends ModelTest {

	@Test
	public void correctExpression() throws Exception{		
		String input = 
				"form test "
				+ "\"What was the selling price?\" sellingPrice MONEY "
				+ "\"Private debts for the sold house:\" privateDebt MONEY "
				+ "\"Value residue:\" valueResidue MONEY = (sellingPrice - privateDebt) "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();
    	   	
    	assertTrue(messages.isEmpty());
	}
	
	@Test
	public void subtractExpressionTypeMismatchExpression() throws Exception{		
		String input = 
				"form test "
				+ "\"What was the selling price?\" sellingPrice MONEY "
				+ "\"Private debts for the sold house:\" privateDebt BOOLEAN "
				+ "\"Value residue:\" valueResidue MONEY = (sellingPrice - privateDebt) "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();

    	assertEquals("substract expression not of type Numeric" , messages.get(0).getMessage());
    	assertEquals("1:154", messages.get(0).getPosition());
    	assertEquals("ERROR",messages.get(0).getType());
    	
    	
    	assertEquals(1, messages.size());
    	assertFalse(messages.isEmpty());
	}
	@Test
	public void addExpressionTypeMismatchExpression() throws Exception{		
		String input = 
				"form test "
				+ "\"What was the selling price?\" sellingPrice MONEY "
				+ "\"Private debts for the sold house:\" privateDebt STRING "
				+ "\"Value residue:\" valueResidue MONEY = (sellingPrice + privateDebt) "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();

    	assertEquals("add expression not of type Numeric" , messages.get(0).getMessage());
    	assertEquals("1:153", messages.get(0).getPosition());
    	assertEquals("ERROR",messages.get(0).getType());
    	
    	
    	assertEquals(1, messages.size());
    	assertFalse(messages.isEmpty());
	}
	@Test
	public void divideExpressionTypeMismatchExpression() throws Exception{		
		String input = 
				"form test "
				+ "\"What was the selling price?\" sellingPrice MONEY "
				+ "\"Private debts for the sold house:\" privateDebt STRING "
				+ "\"Value residue:\" valueResidue MONEY = (sellingPrice / privateDebt) "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();

    	assertEquals("divide expression not of type Numeric" , messages.get(0).getMessage());
    	assertEquals("1:153", messages.get(0).getPosition());
    	assertEquals("ERROR",messages.get(0).getType());
    	
    	
    	assertEquals(1, messages.size());
    	assertFalse(messages.isEmpty());
	}
	@Test
	public void multiplyExpressionTypeMismatchExpression() throws Exception{		
		String input = 
				"form test "
				+ "\"What was the selling price?\" sellingPrice MONEY "
				+ "\"Private debts for the sold house:\" privateDebt BOOLEAN "
				+ "\"Value residue:\" valueResidue MONEY = (sellingPrice * privateDebt) "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();

    	assertEquals("multiply expression not of type Numeric" , messages.get(0).getMessage());
    	assertEquals("1:154", messages.get(0).getPosition());
    	assertEquals("ERROR",messages.get(0).getType());
    	
    	
    	assertEquals(1, messages.size());
    	assertFalse(messages.isEmpty());
	}
	
	@Test
	public void identifierNotDeclaredAndQuestionMismatchExpression() throws Exception{		
		String input = 
				"form test "
				+ "\"What was the selling price?\" sellingPrice MONEY "
				+ "\"Private debts for the sold house:\" privateDebt BOOLEAN "
				+ "\"Value residue:\" valueResidue MONEY = (sellingPrice2 - privateDebt) "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();

    	assertEquals("identifier is not declared" , messages.get(0).getMessage());
    	assertEquals("1:154", messages.get(0).getPosition());
    	assertEquals("ERROR",messages.get(0).getType());
    	
    	assertEquals("question type mismatch" , messages.get(1).getMessage());
    	assertEquals("1:115", messages.get(1).getPosition());
    	assertEquals("ERROR",messages.get(1).getType());
    	assertEquals(2, messages.size());
    	assertFalse(messages.isEmpty());
	}
}
