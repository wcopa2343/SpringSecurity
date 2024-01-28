package com.demo.dh.security.util;

import lombok.Getter;

import java.util.List;
import java.util.Arrays;


@Getter
public enum Role {
    CUSTOMER(Arrays.asList(Permission.READ_ALL_PRODUCTS)),

    ADMINISTRATOR(Arrays.asList(Permission.READ_ALL_PRODUCTS, Permission.SAVE_ONE_PRODUCT));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
