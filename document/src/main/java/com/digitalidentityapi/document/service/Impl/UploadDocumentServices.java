package com.digitalidentityapi.document.service.Impl;

import com.digitalidentityapi.document.entity.NotificationMessage;
import com.digitalidentityapi.document.entity.UpdateDocument;
import com.digitalidentityapi.document.service.UploadDocumentI;
import com.digitalidentityapi.document.utils.rabbit.RabbitPublishMessage;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static com.digitalidentityapi.document.constants.documentConstants.*;

@Service
public class UploadDocumentServices implements UploadDocumentI {
    private final RabbitPublishMessage rabbitPublishMessage;
    private final Storage storage;

    @Autowired
    public UploadDocumentServices(RabbitPublishMessage rabbitPublishMessage, Storage storage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
        this.storage = storage;
    }


    @Override
    public void uploadDocument(String message) {
        JSONObject messageJson = new JSONObject(message);
        String objectName = messageJson.getString("nombreArchivo");
        byte[] archivoBytes = Base64.getDecoder().decode(messageJson.getString("base64file"));
        BlobId blobId = BlobId.of(BUCKETNAME, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, archivoBytes);
        URL signedUrl = storage.signUrl(blobInfo, 1, TimeUnit.HOURS, Storage.SignUrlOption.withV4Signature());
        UpdateDocument updateDocument = new UpdateDocument(messageJson.getString("email"), messageJson.getString("tipoDocumento"), messageJson.getString("nombreArchivo"), signedUrl.toString());
        rabbitPublishMessage.sendMessageToQueueUpdate(DOCUMENTUPDATEQUEUE,updateDocument.toString());
        NotificationMessage notificationMessage = new NotificationMessage(messageJson.getString("email"), "Tu documento fue registrado con éxito y lo encontrarás en la siguiente ruta: " + signedUrl);
        rabbitPublishMessage.sendMessageToQueue(NOTIFICATIONSQUEUE, notificationMessage.toString());
    }
}
