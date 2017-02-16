package com.mcsa;

import java.util.ArrayList;

/**
 * Created by sotos on 16/2/2017.
 */
public class TreeTest {

    TreeTest() {
        TreeNode<String> testtree = new TreeNode<>("root");
        TreeNode<String> child1 = new TreeNode<>("child1");
        TreeNode<String> child2 = new TreeNode<>("child2");
        TreeNode<String> child3 = new TreeNode<>("child3");
        ArrayList<TreeNode<String>> children = new ArrayList<TreeNode<String>>() {{
            add(child1);
            add(child2);
            add(child3);
        }};
        testtree.addChildren(children);

        TreeNode<String> child4 = new TreeNode<>("child4");
        TreeNode<String> child5 = new TreeNode<>("child5");
        TreeNode<String> child6 = new TreeNode<>("child6");
        ArrayList<TreeNode<String>> children1 = new ArrayList<TreeNode<String>>() {{
            add(child4);
            add(child5);
            add(child6);
        }};
        child2.addChildren(children1);

        parseTree(testtree);
    }

    private static void parseTree (TreeNode<String> printChild) {

        System.out.println(printChild.getValue() );
        if (printChild.getChildren() != null) {

            for (int i =0; i < printChild.getChildren().size(); i++) {
                parseTree(printChild.getChild(i));
            }
        }

    }
}
