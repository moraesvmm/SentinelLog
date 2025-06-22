package model;

import java.time.LocalDateTime;

public class LogEntry {
    private LocalDateTime timestamp;
    private String ip;
    private String message;
    private String eventType;

    public LogEntry(LocalDateTime timestamp, String ip, String message, String eventType) {
        this.timestamp = timestamp;
        this.ip = ip;
        this.message = message;
        this.eventType = eventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getIp() {
        return ip;
    }

    public String getMessage() {
        return message;
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "timestamp=" + timestamp +
                ", ip='" + ip + '\'' +
                ", eventType='" + eventType + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
