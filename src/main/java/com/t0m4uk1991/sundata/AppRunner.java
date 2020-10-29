package com.t0m4uk1991.sundata;

import com.t0m4uk1991.sundata.api.ApiClientWrapper;
import com.t0m4uk1991.sundata.model.SunDayData;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppRunner {
    private static final String SERVICE_URL_TEMPLATE = "%s/json?lat=%s&lng=%s&date=%s&formatted=0";
    private static final String CSV_HEADER = "sunrise,sunset,day_length\n";

    public static void main(String[] args ) throws IOException {
        final LocalDate ldt = AppConfig.getStartDate();
        final ApiClientWrapper<SunDayData> client = new ApiClientWrapper<>(SunDayData.class);

        final String data = IntStream.range(0, AppConfig.getNumberOfDays() - 1)
                .mapToObj(dayNumber -> fetchDataFromApi(ldt.plus(dayNumber, ChronoUnit.DAYS), client))
                .map(AppRunner::getCSVRow)
                .collect(Collectors.joining("\n"));

        final byte[] csvData = new StringBuilder(CSV_HEADER)
                .append(data)
                .toString()
                .getBytes();
        Files.write(Paths.get(AppConfig.getResultsFilename()), csvData);
    }

    @SneakyThrows
    private static SunDayData fetchDataFromApi(LocalDate ldt, ApiClientWrapper<SunDayData> client) {
        final String url = String.format(SERVICE_URL_TEMPLATE, AppConfig.getServiceUrl(),
                AppConfig.getLat(),
                AppConfig.getLong(),
                ldt);

        return client.execute(url);
    }

    private static String getCSVRow(SunDayData item) {
        return String.format("%s,%s,%s",
                convertToLocalTime(item.getSunrise()),
                convertToLocalTime(item.getSunset()),
                LocalTime.ofSecondOfDay(item.getDayLength()));
    }

    private static LocalDateTime convertToLocalTime(String date) {
        return LocalDateTime.ofInstant(
                Instant.parse(date.replace("+00:00", "Z")),
                ZoneId.of(AppConfig.getTimeZone())
        );
    }
}
