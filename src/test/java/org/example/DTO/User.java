package org.example.DTO;

import org.example.Util.Base;

public class User {
    public String token;
    private String email;
    private String senha;

    public User(){
        email = "usuario@email.com";
        senha = "123456";
    }

    public String toJsonRequest(){
        String requestBody = "{" +
                "\"email\" : \""+email+"\"," +
                "\"senha\" : \""+senha+"\"" +
                "}";
        return requestBody;
    }
}
