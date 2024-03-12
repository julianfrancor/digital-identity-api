package com.digitalidentityapi.operators.entity;

import java.math.BigInteger;

public class CitizenUnregister {
    public BigInteger id;
    public String operatorId;
    public String operatorName;

    public CitizenUnregister(BigInteger id, String operatorId, String operatorName) {
        this.id = id;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
