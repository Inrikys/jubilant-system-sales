package com.study.security.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class StandardError {
    @Getter
    private List<String> errors;

    public StandardError(String error) {
        this.errors = Arrays.asList(error);
    }

    public StandardError(List<String> errors) {
        this.errors = errors;
    }
}
