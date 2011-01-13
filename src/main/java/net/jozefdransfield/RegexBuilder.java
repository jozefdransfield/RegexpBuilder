package net.jozefdransfield;

import java.util.regex.Pattern;

public class RegexBuilder {

    private final StringBuffer regexp;

    public RegexBuilder() {
        regexp = new StringBuffer();
    }


    public Pattern compile() {
        return Pattern.compile(regexp.toString());
    }

    public String toString() {
        return regexp.toString();
    }


    public RegexBuilder matchString(String s) {
        regexp.append(s);
        return this;
    }

    public RegexBuilder matchDigit() {
        regexp.append("\\d");
        return this;
    }

    public RegexBuilder matchAny() {
        regexp.append(".");
        return this;
    }

    public RegexBuilder oneOrMore() {
        regexp.append("+");
        return this;
    }

    public RegexBuilder zeroOrMore() {
        regexp.append("*");
        return this;
    }

    public RegexBuilder onceOrNever() {
        regexp.append("?");
        return this;
    }

    public RegexBuilder nTimes(int n) {
        regexp.append("{");
        regexp.append(n);
        regexp.append("}");
        return this;
    }

    public RegexBuilder or() {
        regexp.append("|");
        return this;
    }

    public RegexBuilder matchWordCharactor() {
        regexp.append("\\w");
        return this;
    }

    public RegexBuilder matchPeriod() {
        regexp.append("\\.");
        return this;
    }

    public RegexBuilder matchBlackSlash() {
        regexp.append("\\");
        return this;
    }

    public RegexBuilder matchNonDigit() {
        regexp.append("\\D");
        return this;
    }

    public RegexBuilder matchWhitespace() {
        regexp.append("\\s");
        return this;
    }

    public RegexBuilder matchNonWhitespace() {
        regexp.append("\\S");
        return this;
    }

    public RegexBuilder matchNonWordCharactor() {
        regexp.append("\\W");
        return this;
    }

    public RegexBuilder matchCharactorClass(CharactorClassBuilder cc) {
        regexp.append(cc.compile());
        return this;
    }

    public RegexBuilder group(RegexBuilder builder) {
        regexp.append("(");
        regexp.append(builder.compile());
        regexp.append(")");
        return this;
    }
}
