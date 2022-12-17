package com.clinic.restapi.dto.nbp;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class NBPCurrencyDto {
    private List<Rates> rates;
}
