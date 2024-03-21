package com.digitalidentityapi.operators.utils.rabbit;

import com.digitalidentityapi.operators.dto.CitizenForTransferDTO;
import com.digitalidentityapi.operators.dto.RecieveCitizenDTO;
import com.digitalidentityapi.operators.entity.Files;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BuildMessageTransfer {

    public String buildMessageTransfer(CitizenForTransferDTO citizenForTransferDTO) {
       return "";
    }
  public String buildMessageTransferDTO (CitizenForTransferDTO citizenForTransferDTO){
      ObjectMapper mapper = new ObjectMapper();
      try {
          String json = mapper.writeValueAsString(citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO());
          System.out.println(json);
          return json;
      } catch (Exception e) {
          return null;
      }
  }
}
