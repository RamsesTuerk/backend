package com.example.backend.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        // Hier fängst du z.B. dein .orElseThrow(() -> new RuntimeException("...")) ab
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        return ResponseEntity.status(500).body("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
    }
}