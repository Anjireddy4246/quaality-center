package com.ts.codemetrics.model.v1;

import lombok.Data;

@Data
public class ValidationModel {
    private String propertyName;
    private String validationMessage;
}
