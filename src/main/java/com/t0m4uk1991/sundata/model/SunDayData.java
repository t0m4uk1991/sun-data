package com.t0m4uk1991.sundata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SunDayData {
    private String sunrise;
    private String sunset;

    @JsonProperty("solar_noon")
    private String solarNoon;

    @JsonProperty("day_length")
    private Integer dayLength;

    @JsonProperty("civil_twilight_begin")
    private String civilTwilightBegin;

    @JsonProperty("civil_twilight_end")
    private String civilTwilightEnd;

    @JsonProperty("nautical_twilight_begin")
    private String nauticalTwilightBegin;

    @JsonProperty("nautical_twilight_end")
    private String nauticalTwilightEnd;

    @JsonProperty("astronomical_twilight_begin")
    private String astronomicalTwilightBegin;

    @JsonProperty("astronomical_twilight_end")
    private String astronomicalTwilightEnd;
}
