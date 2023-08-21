package com.basiq.ui.tests.enums;

public enum Institution {

    PIED_PIPER("Pied Piper", "AU00002"),
    HOOLI("Hooli", "AU00000");

    private final String name;
    private final String id;

    Institution(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
