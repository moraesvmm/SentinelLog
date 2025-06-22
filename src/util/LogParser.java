package util;

import model.LogEntry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Locale;

public class LogParser {

    private static final Pattern IP_PATTERN = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)");

    public static LogEntry parseLine(String line) {
        LocalDateTime timestamp = parseTimestamp(line);
        if (timestamp == null) return null;

        // Regex para extrair IP
        Pattern ipPattern = Pattern.compile("from ([\\d.]+)");
        Matcher ipMatcher = ipPattern.matcher(line);

        String ip = null;
        if (ipMatcher.find()) {
            ip = ipMatcher.group(1);
        }

        // Identificar tipo de evento
        String eventType = null;
        if (line.contains("Failed password")) {
            eventType = "FAILED_LOGIN";
        } else if (line.contains("Accepted password")) {
            eventType = "SUCCESSFUL_LOGIN";
        } else if (line.contains("Invalid user")) {
            eventType = "INVALID_USER";
        }

        if (ip != null && eventType != null) {
            return new LogEntry(timestamp, ip, line, eventType);
        } else {
            return null; // ignora entradas que não conseguimos interpretar
        }
    }


    public static String extractIp(String line) {
        Matcher matcher = IP_PATTERN.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static LocalDateTime parseTimestamp(String line) {
        try {
            Pattern pattern = Pattern.compile("([A-Z][a-z]{2})\\s+(\\d{1,2})\\s+(\\d{2}:\\d{2}:\\d{2})");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String month = matcher.group(1);
                String day = matcher.group(2);
                String time = matcher.group(3);
                int year = LocalDateTime.now().getYear();

                String fullDate = year + " " + month + " " + day + " " + time;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM d HH:mm:ss", Locale.ENGLISH);
                return LocalDateTime.parse(fullDate, formatter);
            }
        } catch (DateTimeParseException e) {
            System.err.println("Erro ao interpretar timestamp: " + e.getMessage());
        }
        return null;
    }



    public static String detectEventType(String line) {
        if (line.contains("Failed password")) {
            return "FAILED_PASSWORD";
        } else if (line.contains("Accepted password")) {
            return "SUCCESS_LOGIN";
        } else if (line.contains("Invalid user")) {
            return "INVALID_USER";
        }
        return "UNKNOWN";
    }
}
