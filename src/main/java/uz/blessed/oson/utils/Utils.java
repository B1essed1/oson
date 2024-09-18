package uz.blessed.oson.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime convertToLocalDateTime(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        // Convert LocalDate to LocalDateTime by adding time (00:00)
        return date.atStartOfDay();

    }

    public static boolean isValidDate(String dateStr) {

        try {
            // Try to parse the string
            LocalDate.parse(dateStr, formatter);
            return true; // Valid date format
        } catch (DateTimeParseException e) {
            return false; // Invalid date format
        }
    }
}
