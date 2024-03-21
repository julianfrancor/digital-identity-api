package com.digitalidentityapi.citizen.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CitizenWithDocumentsTransferInfoDTO {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String callbackUrl;
    private List<DocumentTransferInfo> files;

    @Getter
    @Setter
    public static class DocumentTransferInfo {
        private String documentTitle;
        private String urlDocument;

        public DocumentTransferInfo(String documentTitle, String urlDocument) {
            this.documentTitle = documentTitle;
            this.urlDocument = urlDocument;
        }
    }
}
