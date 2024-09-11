package com.techgirl.quiz_app.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetail {
    protected DataResponseDetail data;
    protected Meta meta;


    public ResponseDetail(String status, String message, String des, Object quiz) {
        this.data = new DataResponseDetail();
        this.data.setStatus(status);
        this.data.setMessage(message);
        this.data.setDescription(des);
        this.data.setQuizInfo(quiz);

        this.meta = new Meta();
        this.meta.setRequestId(UUID.randomUUID().toString());
        this.meta.setTimestamp(Instant.now().toString());
    }

}
