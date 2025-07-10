package com.weather.aggregator.mappers.responses;

import java.time.LocalDateTime;

public class HealthResponse {
    private LocalDateTime timestamp;
    private String status;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
