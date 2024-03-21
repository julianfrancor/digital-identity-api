package com.digitalidentityapi.operators.entity;

import java.math.BigInteger;
import java.util.List;

public class CitizenRecieveOperator {
    public BigInteger id;
    public String name;
    public String address;
    public String email;
    public String callbackUrl;
    public List<Files> files;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
