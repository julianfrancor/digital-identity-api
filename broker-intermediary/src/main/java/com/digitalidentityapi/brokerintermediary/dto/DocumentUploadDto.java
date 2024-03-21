package com.digitalidentityapi.brokerintermediary.dto;

import lombok.Data;

@Data
public class DocumentUploadDto {
    private String nombreArchivo;
    private String tipoDocumento;
    private String email;
    private String base64file;
}
