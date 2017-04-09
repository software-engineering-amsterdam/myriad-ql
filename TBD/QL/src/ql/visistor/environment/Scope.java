package ql.visistor.environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 14-3-2017.
 */
public class Scope {

    private final Scope parent;

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public Scope getParent() {
        return parent;
    }

    public List<Scope> getScopes() {
        List<Scope> scopes = new ArrayList<>();
        Scope current = this;
        do{
            scopes.add(current);
            current = current.parent;
        }while (current != null);
        return scopes;
    }

}
