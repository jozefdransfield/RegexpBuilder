package net.jozefdransfield;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexBuilderTest {


    @Test
    public void shouldMatchString() {
        RegexBuilder regexBuilder = new RegexBuilder();
        regexBuilder.matchString("y");

        assertFalse(runMatch(regexBuilder, "x"));
        assertTrue(runMatch(regexBuilder, "y"));
    }

    @Test
    public void shouldMatchDigit() {
        RegexBuilder regexBuilder = new RegexBuilder();
        regexBuilder.matchDigit();

        assertFalse(runMatch(regexBuilder, "x"));
        assertTrue(runMatch(regexBuilder, "8"));
    }

    @Test
    public void shouldMatchAny() {
        RegexBuilder regexBuilder = new RegexBuilder();
        regexBuilder.matchAny();

        assertFalse(runMatch(regexBuilder, ""));
        assertTrue(runMatch(regexBuilder, "8"));
    }

    @Test
    public void shouldMatchWordCharactor() {
        RegexBuilder regexBuilder = new RegexBuilder();
        regexBuilder.matchWordCharactor();

        assertFalse(runMatch(regexBuilder, " "));
        assertTrue(runMatch(regexBuilder, "w"));
    }

    @Test
    public void shouldMatchEmailAddress() {
        RegexBuilder regexBuilder = new RegexBuilder();

        regexBuilder.matchWordCharactor().oneOrMore().matchString("@").matchWordCharactor().oneOrMore().matchPeriod().matchWordCharactor().oneOrMore();

        assertFalse(runMatch(regexBuilder, "teststring"));
        assertFalse(runMatch(regexBuilder, "test@string"));
        assertFalse(runMatch(regexBuilder, "tesstrin.g"));
        assertTrue(runMatch(regexBuilder, "test@test.com"));
    }

    @Test
    public void shouldMatchTag() {
        RegexBuilder regexBuilder = new RegexBuilder();

        regexBuilder.matchString("\\<").matchWordCharactor().oneOrMore().matchString("\\>");

        assertFalse(runMatch(regexBuilder, "<html"));
        assertTrue(runMatch(regexBuilder, "<html>"));
    }

    @Test
    public void shouldMatchCharactorClass() {
        RegexBuilder regexBuilder = new RegexBuilder();

        regexBuilder.matchCharactorClass(new CharactorClassBuilder().fromTo("a", "d"));

        assertFalse(runMatch(regexBuilder, "efgh"));
        assertTrue(runMatch(regexBuilder, "abc"));
    }

    @Test
    public void shouldMatchMultipleRangeCharactorClass() {
        RegexBuilder regexBuilder = new RegexBuilder();

        regexBuilder.matchCharactorClass(new CharactorClassBuilder().fromTo("a", "d").fromTo("A", "D"));

        assertFalse(runMatch(regexBuilder, "EF"));
        assertTrue(runMatch(regexBuilder, "abc"));
        assertTrue(runMatch(regexBuilder, "ABC"));
    }

    @Test
    public void shouldMatchCharactorList() {
        RegexBuilder regexBuilder = new RegexBuilder();

        regexBuilder.matchCharactorClass(new CharactorClassBuilder().list("ad"));

        assertFalse(runMatch(regexBuilder, "bc"));
        assertTrue(runMatch(regexBuilder, "abc"));
        assertTrue(runMatch(regexBuilder, "bcd"));
    }

    @Test
    public void shouldMatchMixOfCharactorClasses() {
        RegexBuilder regexBuilder = new RegexBuilder();

        regexBuilder.matchCharactorClass(new CharactorClassBuilder().list("ad").fromTo("q", "x"));

        assertFalse(runMatch(regexBuilder, "bc"));
        assertTrue(runMatch(regexBuilder, "abc"));
        assertTrue(runMatch(regexBuilder, "bcd"));
        assertTrue(runMatch(regexBuilder, "s"));
    }

    @Test
    public void shouldStripTag() {
        RegexBuilder regexBuilder = new RegexBuilder();

        regexBuilder.matchString("<").matchWordCharactor().oneOrMore().matchString(">");

        assertEquals("Some in here", "Some<html> in here".replaceAll(regexBuilder.toString(), ""));
    }


    @Test
    public void shouldStripComplexTag() {
        RegexBuilder regexBuilder = new RegexBuilder();

        regexBuilder.group(
            new RegexBuilder().matchString("<").matchAny().oneOrMore().matchString(">")
        ).or().group(
            new RegexBuilder().matchString("#").matchAny().oneOrMore().matchString("#"));


        System.out.println(regexBuilder);
        assertEquals("Some in here", "Some<html> in#tag# here".replaceAll(regexBuilder.toString(), ""));
    }

    private boolean runMatch(RegexBuilder regexBuilder, String string) {
        System.out.println(regexBuilder);
        Pattern p = regexBuilder.compile();
        Matcher m = p.matcher(string);
        return m.find();
    }
}


