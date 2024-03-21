package com.digitalidentityapi.brokerintermediary.Message;

import com.digitalidentityapi.brokerintermediary.dto.DocumentDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentUploadMessage {
    private String nombreArchivo;
    private String tipoDocumento;
    private String email;
    private String base64file;

    public DocumentUploadMessage(String nombreArchivo, String tipoDocumento, String email, String base64file) {
        this.nombreArchivo = nombreArchivo;
        this.tipoDocumento = tipoDocumento;
        this.email = email;
        this.base64file = base64file;
    }
}

