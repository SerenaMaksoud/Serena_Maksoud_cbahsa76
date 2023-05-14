package com.example.Anywr.datahandlers.models.Responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "status",
        "ResponseCode"
})
@Component
@Data
@Builder
@NoArgsConstructor
@ResponseBody
public class BaseResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("ResponseCode")
    private HttpStatus responseCode;

    public BaseResponse(String message, Integer status, HttpStatus responseCode) {
        this.message = message;
        this.status = status;
        this.responseCode = responseCode;
    }
}
