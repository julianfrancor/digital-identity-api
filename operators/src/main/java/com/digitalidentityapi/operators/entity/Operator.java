package com.digitalidentityapi.operators.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Operator {
    private String _id;
    private String operatorName;
    private String transferAPIURL;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getTransferAPIURL() {
        return transferAPIURL;
    }

    public void setTransferAPIURL(String transferAPIURL) {
        this.transferAPIURL = transferAPIURL;
    }
}


