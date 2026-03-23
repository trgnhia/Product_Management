package org.example.product_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ValidationResponse {
    private String message;
    private int status;
    private String error;
    private Map<String, String > errors;
}
