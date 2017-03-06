package visitor;

import org.testng.annotations.Test;

public class NumberVisitorTest {
    @Test
    public void testVisitor() throws Exception {
        Value a = new DoubleNumber(10.0);
        Value b = new IntNumber(12);
        VisitorBase visitMe = new NumberVisitor();
        a.accept(visitMe);
        b.accept(visitMe);
    }
}