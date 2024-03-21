package com.digitalidentityapi.operators.dto;

import java.math.BigInteger;

public class UnregistredCitizenDTO {
    private BigInteger id;
    private String email;

    public UnregistredCitizenDTO(BigInteger id, String email) {
        this.id = id;
        this.email = email;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"email\":" + "\"" + email + "\"}";
    }
}
