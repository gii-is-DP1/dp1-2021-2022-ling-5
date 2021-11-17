package com.example.accessingdatamysql.game;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum State {
    IN_PROGRESS("IN_PROGRESS"), UNSTARTED("UNSTARTED"), FINISHED("FINISHED");

    private final String value;
    private final static Map<String, State> CONSTANTS = new HashMap<String, State>();

    static {
        for (State ps : values()) {
            CONSTANTS.put(ps.value, ps);
        }
    }

    private State(String value) {
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
    public static State fromValue(String value) {
        State constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
