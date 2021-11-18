package com.example.accessingdatamysql.user;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlayerState {
    PLAYING("PLAYING"), WAITING_TO_PLAY("WAITING_TO_PLAY"), SPECTATING("SPECTATING"), NO_PLAY("NO_PLAY");

    private final String value;
    private final static Map<String, PlayerState> CONSTANTS = new HashMap<String, PlayerState>();

    static {
        for (PlayerState ps : values()) {
            CONSTANTS.put(ps.value, ps);
        }
    }

    private PlayerState(String value) {
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
    public static PlayerState fromValue(String value) {
        PlayerState constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}