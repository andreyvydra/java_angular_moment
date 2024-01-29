package ru.lab4.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShotRequest {
    @NotNull(message = "X must not be null")
    @Digits(integer = 1, fraction = 15, message = "X must be decimal with a maximum of 15 fractional digits and 1 integral digit")
    Double x;
    @NotNull(message = "Y must not be null")
    @Digits(integer = 1, fraction = 15, message = "Y must be decimal with a maximum of 15 fractional digits and 1 integral digit")
    Double y;
    @NotNull(message = "R must not be null")
    @Digits(integer = 1, fraction = 15, message = "R must be decimal with a maximum of 15 fractional digits and 1 integral digit")
    Double r;

    @JsonCreator
    public ShotRequest(@JsonProperty("x") Double x,
                       @JsonProperty("y") Double y,
                       @JsonProperty("r") Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}