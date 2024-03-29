package com.digitalidentityapi.operators.entity;

import java.math.BigInteger;

public class CitizenRegister {
    private BigInteger id;
    private String name;
    private String address;
    private String email;
    private String operatorId;
    private String OperatorName;

    public CitizenRegister(BigInteger id, String name, String address, String email, String operatorId, String operatorName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.operatorId = operatorId;
        OperatorName = operatorName;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return OperatorName;
    }

    public void setOperatorName(String operatorName) {
        OperatorName = operatorName;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"name\":\"" + name + '\"' +
                ", \"address\":\"" + address + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"operatorId\":\"" + operatorId + '\"' +
                ", \"OperatorName\":\"" + OperatorName + '\"' +
                '}';
    }
}
