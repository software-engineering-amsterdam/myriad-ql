package org.ql.typechecker.issues;

import java.util.ArrayList;
import java.util.List;

public class IssuesStorage {

    private final List<Issue> errors = new ArrayList<>();
    private final List<Issue> warnings = new ArrayList<>();

    public void addError(Issue error) {
        addIssue(error, errors);
    }

    public void addWarning(Issue warning) {
        addIssue(warning, warnings);
    }

    public List<Issue> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    private void addIssue(Issue issue, List<Issue> issues) {
        issues.add(issue);
    }

    public List<Issue> getWarnings() {
        return warnings;
    }
}
