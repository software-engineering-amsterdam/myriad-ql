package sc.ql.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import sc.ql.checkform.CheckForm;
import sc.ql.checkform.Message;
import sc.ql.model.Form;

public class QuestionTest extends ModelTest {
	
	@Test
	public void correctQuestions() throws Exception{		
		String input = 
				"form test "
				+ "\"Did you sell a house in 2010?\" hasSoldHouse BOOLEAN "
				+ "\"Did you buy a house in 2010?\" hasBoughtHouse BOOLEAN "
				+ "\"Did you enter a loan?\" hasMaintLoan BOOLEAN "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();
    	   	
    	assertTrue(messages.isEmpty());
	}
	
	
	@Test
	public void duplicateLabelQuestions() throws Exception{		
		String input = 
				"form test "
				+ "\"Did you sell a house in 2010?\" hasSoldHouse BOOLEAN "
				+ "\"Did you buy a house in 2010?\" hasBoughtHouse BOOLEAN "
				+ "\"Did you sell a house in 2010?\" hasMaintLoan BOOLEAN "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();
    	   	
    	assertEquals("duplicate label detected" , messages.get(0).getMessage());
    	assertEquals("1:117", messages.get(0).getPosition());
    	assertEquals("WARNING",messages.get(0).getType());
    	assertEquals(1, messages.size());
    	assertFalse(messages.isEmpty());	
	}
	@Test
	public void duplicateIdentifierQuestions() throws Exception{		
		String input = 
				"form test "
				+ "\"Did you sell a house in 2010?\" hasSoldHouse BOOLEAN "
				+ "\"Did you buy a house in 2010?\" hasSoldHouse BOOLEAN "
				+ "\"Did you enter a loan?\" hasMaintLoan BOOLEAN "
				+ "endform";
		
		Form form = createForm(input);
    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();

    	assertEquals("duplicate identifier detected" , messages.get(0).getMessage());
    	assertEquals("1:63", messages.get(0).getPosition());
    	assertEquals("ERROR",messages.get(0).getType());
    	assertEquals(1, messages.size());
    	assertFalse(messages.isEmpty());	
	}

}
