package visitor;

import inheritance.Count;
import inheritance.QuickCount;
import org.testng.annotations.Test;

public class CountVisitorTest {
    @Test
    public void testVisitor() throws Exception {
        Count a = new Count();
        Count b = new QuickCount();
        CountVisitor visitMe = new CountVisitor();
        a.accept(visitMe);
        b.accept(visitMe);
    }


}