package com.clinic.restapi;

import com.clinic.restapi.dto.nbp.NBPCurrencyDto;
import com.clinic.restapi.dto.weather.WeatherMainDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restapi")
public class RestApiController {

    private final RestApiService restApiService;

    RestApiController(final RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    @GetMapping("/weather")
    public ResponseEntity<Optional<WeatherMainDto>> getCurrentWeather(@RequestParam String location){
        return ResponseEntity.ok(restApiService.getCurrentWeather(location));
    }

    @GetMapping("/nbp")
    public ResponseEntity<List<NBPCurrencyDto>> getCurrency(){
        return ResponseEntity.ok(restApiService.getCurrency());
    }
}
