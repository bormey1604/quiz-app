package com.techgirl.quiz_app.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponseDetail extends DataResponse{
    Object quizInfo;
}
