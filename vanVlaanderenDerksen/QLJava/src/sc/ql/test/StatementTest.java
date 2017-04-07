package sc.ql.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import sc.ql.checkform.CheckForm;
import sc.ql.checkform.Message;
import sc.ql.model.Form;

public class StatementTest extends ModelTest {	
	
	@Test
	public void correctIfthenStatement() throws Exception{		
		String input = 
				"form test "
				+ "\"Did you sell a house in 2010?\" hasSoldHouse BOOLEAN "
					+ "if(hasSoldHouse) "
						+ "\"What was the selling price?\" sellingPrice MONEY "
						+ "endif "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();
    	   	
    	assertTrue(messages.isEmpty());
	}
	
	@Test
	public void identifierNotDeclaredIfthenStatement() throws Exception{		
		String input = 
				"form test "
				+ "\"Did you sell a house in 2010?\" hasSoldHouse BOOLEAN "
					+ "if(hasSoldHouse2) "
						+ "\"What was the selling price?\" sellingPrice MONEY "
						+ "endif "
				+ "\"Did you sell a house in 2012?\" hasSoldHouse2 BOOLEAN "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();

    	assertEquals("identifier is not declared" , messages.get(0).getMessage());
    	assertEquals("1:66", messages.get(0).getPosition());
    	assertEquals("ERROR",messages.get(0).getType());
    	assertEquals(1, messages.size());
    	assertFalse(messages.isEmpty());	
	}
	
	@Test
	public void identifierNotDeclaredAndIfThenConditionErrorIfthenStatement() throws Exception{		
		String input = 
				"form test "
				+ "\"Did you sell a house in 2010?\" hasSoldHouse BOOLEAN "
					+ "if(hasSoldHouse2) "
						+ "\"What was the selling price?\" sellingPrice MONEY "
						+ "endif "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();
    	
    	assertEquals("identifier is not declared" , messages.get(0).getMessage());
    	assertEquals("1:66", messages.get(0).getPosition());
    	assertEquals("ERROR",messages.get(0).getType());
    	
    	assertEquals("if-then condition type error" , messages.get(1).getMessage());
    	assertEquals("1:63", messages.get(1).getPosition());
    	assertEquals("ERROR",messages.get(1).getType());
    	
    	assertEquals(2, messages.size());
    	assertFalse(messages.isEmpty());	
	}
	
	@Test
	public void correctIfthenelseStatement() throws Exception{		
		String input = 
				"form test "
				+ "\"Did you sell a house in 2010?\" hasSoldHouse BOOLEAN "
					+ "if(hasSoldHouse) "
						+ "\"What was the selling price?\" sellingPrice MONEY "
					+ "else "
						+ "\"What is the price of your house?\" offeringPrice MONEY "
						+ "endif "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();
    	   	
    	assertTrue(messages.isEmpty());
	}
}
