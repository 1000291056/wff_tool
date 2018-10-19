package com.wff.wff_tool.mock;

public class Mock {

    String getPersonName(Person person) {
        if (person == null) return "";
        return person.name;
    }
}
