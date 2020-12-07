package com.example.demo.controller;

import com.example.demo.service.BIService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/bi")
@Api(value = "BI", tags = "BI")
public class BIController {

    private final BIService BIService;

    @Autowired
    public BIController(BIService BIService) {
        this.BIService = BIService;
    }

    @GetMapping(value = "/periodData/{duration}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Double> getAllMonths(@PathVariable(value = "duration") final String duration) {
        return BIService.getMonthsList(duration);
    }

}
