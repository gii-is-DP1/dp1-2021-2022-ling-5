package com.example.accessingdatamysql.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLoggin {
    private String nickname;
    private String password;
    public RequestLoggin(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }
}
