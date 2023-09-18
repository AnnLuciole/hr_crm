package com.company.hr_crm.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Department implements EnumClass<String> {

    HR("hr"),
    IT("it");

    private String id;

    Department(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Department fromId(String id) {
        for (Department at : Department.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}