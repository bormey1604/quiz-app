package com.techgirl.quiz_app.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    protected DataResponse data;
    protected Meta meta;


    public Response(String status, String message, String des) {
        this.data = new DataResponse();
        this.data.setStatus(status);
        this.data.setMessage(message);
        this.data.setDescription(des);

        this.meta = new Meta();
        this.meta.setRequestId(UUID.randomUUID().toString());
        this.meta.setTimestamp(Instant.now().toString());
    }

}


