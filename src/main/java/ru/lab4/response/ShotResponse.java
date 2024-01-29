package ru.lab4.response;

import lombok.Value;

@Value
public class ShotResponse {
    Long id;
    Double x;
    Double y;
    Double r;
    Boolean inArea;
    String shotTime;
}
