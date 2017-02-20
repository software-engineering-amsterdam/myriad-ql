package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FunctionPointerTest {
    @Test
    public void testFunctionPointer() throws Exception {
        List<String> first = doSomeAst(new TwoStrings());
        Assert.assertEquals(first.size(), 2, "Expected two strings");
        List<String> second = doSomeAst(new ThreeStrings());
        Assert.assertEquals(second.size(), 3, "Expected three strings");
    }

    public List<String> doSomeAst(ListFunction lf) {
        return lf.function("Goodbye");
    }
}

interface ListFunction {
    List<String> function(String suffix);
}

class TwoStrings implements ListFunction {
    public List<String> function(String suffix) {
        List<String> a = new ArrayList<>();
        a.add("Hello" + suffix);
        a.add("Goodbye" + suffix);
        return a;
    }
}


class ThreeStrings implements ListFunction {
    public List<String> function(String suffix) {
        List<String> a = new ArrayList<>();
        a.add("One" + suffix);
        a.add("Two" + suffix);
        a.add("Three" + suffix);
        return a;
    }


}
