package net.jozefdransfield;

public class CharactorClass {

    private String from;
    private String to;

    public CharactorClass from(String from) {
        this.from = from;
        return this;
    }

    public CharactorClass to(String to) {
        this.to = to;
        return this;
    }

    public String compile() {
        return "["+from+"-"+to+"]";
    }
}
