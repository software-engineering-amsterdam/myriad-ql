package UvA.Gamma.Validation;

/**
 * Created by Tjarco, 17-04-17.
 */
class ErrorHandler {
    static void cyclicDependency(String identifier1, String identifier2) {
        System.err.println("Cyclic dependency between: " + identifier1 + " and " + identifier2);
        System.exit(1);
    }

    static void incompatibleType(String identifier) {
        System.err.println("Incompatible types in expression: " + identifier);
        System.exit(1);
    }

    static void duplicateIdentifier(String identifier) {
        System.err.println("Duplicate identifier: " + identifier);
        System.exit(1);
    }

    static void duplicateLabel(String identifier) {
        System.err.println("Duplicate label for: " + identifier);
    }

    static void invalidReference(String identifier) {
        System.err.println("Referenced non existing identifier: " + identifier);
        System.exit(1);
    }

}
