//package test.atom;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import ast.atom.StringAtom;
//import static org.hamcrest.CoreMatchers.*;
//
//
//public class String {
//
//	StringAtom strABC = new StringAtom("ABC", 1);
//	StringAtom strABC2 = new StringAtom("ABC", 1);
//	StringAtom strBCD = new StringAtom("BCD" ,1);
//
//	@Test
//	public void constructor() {
//
//		assertThat(strABC.getString(), is(strABC.getString()));
//		assertThat(strABC.getString(), is(strABC2.getString()));
//		assertThat(strABC.getString(), is(not(strBCD.getString())));
//
//		assertNull(strABC.getNumber());
//		assertNull(strABC.getValue());
//	}
//
//	@Test
//	public void operators() {
//
//		assertTrue(strABC.eq(strABC2).getValue());
//		assertFalse(strABC.eq(strBCD).getValue());
//
//		assertFalse(strABC.notEq(strABC2).getValue());
//		assertTrue(strABC.notEq(strBCD).getValue());
//		// TODO add others
//
//	}
//
//
//}
