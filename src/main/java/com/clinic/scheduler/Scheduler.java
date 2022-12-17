package com.clinic.scheduler;

import com.clinic.restapi.RestApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final RestApiService restApiService;

    Scheduler(final RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void updateCurrency(){
        restApiService.getEurCurrency();
        restApiService.getUsdCurrency();
    }
}
