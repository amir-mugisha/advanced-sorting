package com.atlab.advanced.sorting.utils;

import java.util.Map;


public class ApiResponse<T> {
    private T data;
    private String message;
    private int statusCode;
    private Map<String, String> availablePaths;

    public ApiResponse(T data, String message, int statusCode, Map<String, String> availablePaths) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
        this.availablePaths = availablePaths;
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getAvailablePaths() {
        return availablePaths;
    }

    public void setAvailablePaths(Map<String, String> availablePaths) {
        this.availablePaths = availablePaths;
    }
}