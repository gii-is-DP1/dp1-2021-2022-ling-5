package com.example.accessingdatamysql.achievement;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AchievementTypes {
    POINTS("POINTS"),POINTSTORRE("POINTSTORRE"),
        POINTSFOSO("POINTSFOSO"),POINTSREGALOS("POINTSREGALO");
    
    private final String value;
    private final static Map<String,AchievementTypes> CONSTANTS = new HashMap<String,AchievementTypes>();

    static{
        for(AchievementTypes at: values()){
            CONSTANTS.put(at.value, at);
        }
    }

    private AchievementTypes(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }

    @JsonValue
    public String value(){
        return this.value;
    }

    @JsonCreator
    public static AchievementTypes fromValue(String value){
        AchievementTypes constant = CONSTANTS.get(value);
        if(constant==null){
            throw new IllegalArgumentException(value);
        } else{
            return constant;
        }
    }
}
