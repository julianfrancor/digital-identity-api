package com.digitalidentityapi.operators.dto;

public class OperationDTO {
    private String operation;
    private DtoRegisterCitizen citizenDto;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public DtoRegisterCitizen getCitizenDto() {
        return citizenDto;
    }

    public void setCitizenDto(DtoRegisterCitizen citizenDto) {
        this.citizenDto = citizenDto;
    }
}
