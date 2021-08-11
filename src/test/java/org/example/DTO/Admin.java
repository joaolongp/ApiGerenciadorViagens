package org.example.DTO;

import org.example.Util.Base;

public class Admin {
    public String token;
    public String email;
    public String senha;

    public Admin(){
        email = "admin@email.com";
        senha = "654321";
    }

    public String toJsonRequest(){
        String requestBody = "{" +
                "\"email\" : \""+email+"\"," +
                "\"senha\" : \""+senha+"\"" +
                "}";
        return requestBody;
    }
}
