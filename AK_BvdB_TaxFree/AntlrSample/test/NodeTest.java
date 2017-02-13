package test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NodeTest {
    @Test
    public void testAddChild() throws Exception {
        Node simpleNode = new FormNode("TestForm", null);
        Node secondNode = new FormNode("SecondForm", simpleNode);
        Assert.assertFalse(simpleNode.addChild(secondNode), "Re-Registering a child should throw an error");
    }


}