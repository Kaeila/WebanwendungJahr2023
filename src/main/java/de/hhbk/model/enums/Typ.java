package de.hhbk.model.enums;

public enum Typ {
    EMPTY("", "Unbekannt"),
    DETACHED_HOUSE("Freistehendes Haus", "Freistehendes Haus"),
    TOWNHOUSE("Stadthaus", "Stadthaus"),
    VILLA("Villa", "Villa"),
    MANSION("Großes Haus", "Großes Haus");

    private final String typName;
    private final String name;

    private Typ(String a, String g) {
        this.typName = a;
        this.name = g;
    }

    public String getTypName() {
        return typName;
    }

    public String getName() {
        return name;
    }
}
