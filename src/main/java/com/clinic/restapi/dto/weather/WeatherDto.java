package com.clinic.restapi.dto.weather;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WeatherDto {
    private WeatherMainDto main;
}
