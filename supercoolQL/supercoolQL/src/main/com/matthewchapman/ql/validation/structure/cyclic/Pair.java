package com.matthewchapman.ql.validation.structure.cyclic;

import java.util.Objects;

/**
 * Created by matt on 16/03/2017.
 */
public class Pair {

    private String start;
    private String end;

    public Pair(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() { return this.start; }
    public String getEnd() { return this.end; }

    @Override
    public String toString() {
        return this.start + " --> " + this.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair input = (Pair) obj;

        return Objects.equals(this.start, input.getStart()) && Objects.equals(this.end, input.getEnd());
    }

    public boolean isReflexive() {
        return this.start.equals(this.end);
    }

    public boolean isTransitive(Pair input) {
        return this.end.equals(input.getStart());
    }
}
