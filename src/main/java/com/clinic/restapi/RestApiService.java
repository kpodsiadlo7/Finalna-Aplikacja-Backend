package com.clinic.restapi;

import com.clinic.restapi.dto.nbp.NBPCurrencyDto;
import com.clinic.restapi.dto.weather.WeatherDto;
import com.clinic.restapi.dto.weather.WeatherMainDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestApiService {
    private static final String WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String WEATHER_API_KEY = "9d4cb4e467f0f7502f293cb1dd44e862";
    private static final String NBP_CURRENCY_URL = "http://api.nbp.pl/api/exchangerates/tables/a/";
    private final RestTemplate restTemplate;
    public Optional<WeatherMainDto> getCurrentWeather(final String location) {
        URI url = UriComponentsBuilder.fromHttpUrl(WEATHER_BASE_URL +"weather?q="+location+"&appid="+WEATHER_API_KEY+"&units=metric").build().encode().toUri();
        WeatherDto weatherResponse = restTemplate.getForObject(url,WeatherDto.class);
        return Optional.ofNullable(weatherResponse).map(WeatherDto::getMain);
    }

    public List<NBPCurrencyDto> getCurrency() {
        URI url = UriComponentsBuilder.fromHttpUrl(NBP_CURRENCY_URL+"?format=json").build().toUri();
        NBPCurrencyDto[] nbpCurrencyResponse = restTemplate.getForObject(url,NBPCurrencyDto[].class);
        return Optional.ofNullable(nbpCurrencyResponse).map(Arrays::asList).orElse(Collections.emptyList());
    }
}
