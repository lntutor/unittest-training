package com.unittest.demo.exception;

public class StudentNotFoundException extends RuntimeException {
    private String message;

    public StudentNotFoundException (String message) {
        this.message = message;
    }
}
