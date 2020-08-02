package com.example.demo.domain.JsonResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JsonResponseError {
    @JsonProperty("code")
    public String code;

    @JsonProperty("description")
    public  String description;
}
