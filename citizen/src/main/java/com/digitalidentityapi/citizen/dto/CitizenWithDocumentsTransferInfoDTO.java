package com.digitalidentityapi.citizen.dto;

import lombok.Data;
import java.util.List;

@Data
public class CitizenWithDocumentsTransferInfoDTO {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String callbackUrl;
    private List<DocumentTransferInfo> files;

    public static class DocumentTransferInfo {
        private String documentTitle;
        private String urlDocument;
    }
}
