package sc.ql.test;

import static org.junit.Assert.*;
import org.junit.Test;
import sc.ql.model.types.*;

public class TypeTest {
	@Test
	public void compairTypes(){
		
        BooleanType firstBooleanType  = new BooleanType();
        BooleanType secondBooleanType = new BooleanType();
        IntegerType firstIntegerType  = new IntegerType();
        IntegerType secondIntegerType = new IntegerType();
        MoneyType firstMoneyType 	  = new MoneyType();
        MoneyType secondMoneyType 	  = new MoneyType();
        StringType firstStringType 	  = new StringType();
        StringType secondStringType   = new StringType();
        
        assertTrue(firstBooleanType.isCompatibleWith(secondBooleanType));
        assertTrue(secondBooleanType.isCompatibleWith(firstBooleanType));

        assertTrue(firstIntegerType.isCompatibleWith(secondIntegerType));
        assertTrue(secondIntegerType.isCompatibleWith(firstIntegerType));
        
        assertTrue(firstMoneyType.isCompatibleWith(secondMoneyType));
        assertTrue(secondMoneyType.isCompatibleWith(firstMoneyType));
        
        assertTrue(firstStringType.isCompatibleWith(secondStringType));
        assertTrue(secondStringType.isCompatibleWith(firstStringType));
	}
    @Test
    public void shouldReturnFalseOnCompareBooleanWithAnIntegerType() {
        BooleanType booleanType = new BooleanType();
        IntegerType integerType = new IntegerType();
        MoneyType moneyType 	= new MoneyType();
        StringType stringType 	= new StringType();

        assertFalse(booleanType.isCompatibleWith(integerType));
        assertFalse(integerType.isCompatibleWith(booleanType));
        
        assertFalse(booleanType.isCompatibleWith(moneyType));
        assertFalse(moneyType.isCompatibleWith(booleanType));
        
        assertFalse(booleanType.isCompatibleWith(stringType));
        assertFalse(stringType.isCompatibleWith(booleanType));
    }
}