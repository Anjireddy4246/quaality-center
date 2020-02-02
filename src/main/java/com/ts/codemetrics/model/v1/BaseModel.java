package com.ts.codemetrics.model.v1;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class BaseModel extends Object {
    private boolean isSuccess = Boolean.TRUE;
    private List<ValidationModel> validations = new ArrayList<>();
}
