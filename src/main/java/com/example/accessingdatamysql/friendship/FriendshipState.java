package com.example.accessingdatamysql.friendship;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FriendshipState {
    FRIENDS("FRIENDS"), REQUESTED("REQUESTED");

    private final String value;
    private final static Map<String, FriendshipState> CONSTANTS = new HashMap<String, FriendshipState>();

    static {
        for (FriendshipState ps : values()) {
            CONSTANTS.put(ps.value, ps);
        }
    }

    private FriendshipState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static FriendshipState fromValue(String value) {
        FriendshipState constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
