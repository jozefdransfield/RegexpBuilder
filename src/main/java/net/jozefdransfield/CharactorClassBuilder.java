package net.jozefdransfield;

import java.util.LinkedList;
import java.util.List;

public class CharactorClassBuilder {

    private StringBuffer regexp;

    public CharactorClassBuilder() {
        regexp = new StringBuffer();
        regexp.append("[");
    }

    public CharactorClassBuilder not() {
        regexp.append("^");
        return this;
    }

    public CharactorClassBuilder fromTo(String from, String to) {
        regexp.append(from);
        regexp.append("-");
        regexp.append(to);
        return this;
    }

    public CharactorClassBuilder list(String chars) {
        regexp.append(chars);
        return this;
    }

    public String compile() {
        regexp.append("]");
        return regexp.toString();
    }
}
