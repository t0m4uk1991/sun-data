package com.t0m4uk1991.sundata;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class AppConfig {
    static private Properties properties;
    static {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("app.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static public String getServiceUrl() {
        return properties.getProperty("api.url");
    }

    static public String getLat() {
        return properties.getProperty("location.lat");
    }

    static public String getLong() {
        return properties.getProperty("location.long");
    }

    static public String getTimeZone() {
        return properties.getProperty("location.timezone");
    }

    static public String getResultsFilename() {
        return properties.getProperty("results.filename");
    }

    static public LocalDate getStartDate() {
        final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(properties.getProperty("period.start"), df);
    }

    static public Integer getNumberOfDays() {
        return Integer.valueOf(properties.getProperty("period.duration"));
    }
}
