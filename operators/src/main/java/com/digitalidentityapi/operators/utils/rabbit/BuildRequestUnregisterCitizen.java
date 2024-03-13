package com.digitalidentityapi.operators.utils.rabbit;

import com.digitalidentityapi.operators.constants.Constants;
import okhttp3.Request;
import okhttp3.RequestBody;

public class BuildRequestUnregisterCitizen {
    public static Request getRequest(RequestBody body){
        return new Request.Builder()
                .url(Constants.URL + Constants.UNREGISTEREDCITIZEN)
                .delete(body)
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
