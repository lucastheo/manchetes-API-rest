package com.example.demo.domain.JsonResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude( JsonInclude.Include.NON_NULL)
public class JsonResponse {

    @JsonProperty("data")
    Object data;

    @JsonProperty("Error")
    JsonResponseError jsonResponseError;
}
