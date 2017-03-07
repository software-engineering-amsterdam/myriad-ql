//package test.atom;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.not;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;
//
//import ast.atom.IntegerAtom;
//
//public class Integer {
//
//	IntegerAtom int1 = new IntegerAtom(1, 1);
//	IntegerAtom int1Copy = new IntegerAtom(1, 1);
//	IntegerAtom int2 = new IntegerAtom(2 ,1);
//
//	@Test
//	public void constructor() {
//
//		assertThat(int1.getNumber(), is(int1.getNumber()));
//		assertThat(int1Copy.getNumber(), is(int1Copy.getNumber()));
//		assertThat(int1.getNumber(), is(not(int2.getNumber())));
//
//		assertNull(int1.getString());
//		assertNull(int1.getValue());
//	}
//
//	@Test
//	public void operators() {
//
//		assertTrue(int1.eq(int1Copy).getValue());
//		assertFalse(int1.eq(int2).getValue());
//
//		assertFalse(int1.notEq(int1Copy).getValue());
//		assertTrue(int1.notEq(int2).getValue());
//		// TODO assertNULL for other operators?
//
//	}
//
//
//}
//
