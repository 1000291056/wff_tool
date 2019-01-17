package com.wff.androidtool.mock;

public class Mock {

    String getPersonName(Person person) {
        if (person == null) return "";
        return person.name;
    }
}
