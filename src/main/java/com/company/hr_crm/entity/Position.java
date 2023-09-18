package com.company.hr_crm.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Position implements EnumClass<String> {

    ADMINISTRATOR("administrator"),
    SENIOR_EMPLOYEE("senior"),
    JUNIOR_EMPLOYEE("junior");

    private String id;

    Position(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Position fromId(String id) {
        for (Position at : Position.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}