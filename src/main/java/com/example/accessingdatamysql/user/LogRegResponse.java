package com.example.accessingdatamysql.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRegResponse {
    private Long id;
    private String rol;

    public LogRegResponse(Long id, String rol){
        this.id = id;
        this.rol = rol;
    }
}
