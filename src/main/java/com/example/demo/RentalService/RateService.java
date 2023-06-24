package com.example.demo.RentalService;

import com.example.demo.RateRepository.RateRepository;
import com.example.demo.model.NbpApiResponse;
import com.example.demo.model.Rate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RateService {

    private final RestTemplate restTemplate;
    private final RateRepository rateRepository;

    public RateService(RestTemplate restTemplate, RateRepository rateRepository) {
        this.restTemplate = restTemplate;
        this.rateRepository = rateRepository;
    }

    public double calculateRateBeetwen(String code , LocalDate dayStart, LocalDate dayEnd) {

        long days = ChronoUnit.DAYS.between(dayStart, dayEnd);

        String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/A/"+code+"/"+dayStart +"/"+dayEnd;

        NbpApiResponse nbpApi = restTemplate.getForObject(NBP_API_URL, NbpApiResponse.class);

        double sum = 0.0;


        assert nbpApi != null;
        for (Rate rate : nbpApi.getRates()) {
            double mid = rate.getMid();
            sum += mid;
        }

        if (days > 0) {
            return sum/days;
        } else {
            return 0.0;
        }
    }
    public void saveRatesToDatabase(NbpApiResponse nbpApi) {
        for (Rate rate : nbpApi.getRates()) {
            rateRepository.save(rate);
        }
    }
}
