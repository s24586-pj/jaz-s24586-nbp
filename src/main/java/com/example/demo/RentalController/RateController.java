package com.example.demo.RentalController;

import com.example.demo.RentalService.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/Rate")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/Test")
    public ResponseEntity<String> TEST() {
        return ResponseEntity.ok("TEST");
    }

    @GetMapping("/{currency}")
    public ResponseEntity<Double> getAverageRate(@PathVariable("currency") String currency,
    @RequestParam(value = "dayStart") LocalDate DayStart, @RequestParam(value = "dayEnd") LocalDate DayEnd)
    {
        double averageRate = rateService.calculateRateBeetwen(currency,DayStart,DayEnd);
        return ResponseEntity.ok(averageRate);
    }

}
