package com.cooperativismo.APICooperativismo.tratamentoErros;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {

    private List<String> message;
    private String details;
    private String developerDetails;
    private HttpStatus status;
    private int code;
    
} 