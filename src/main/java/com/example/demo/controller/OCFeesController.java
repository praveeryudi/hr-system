package com.example.demo.controller;

import com.example.demo.entity.OCFee;
import com.example.demo.service.OCFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/oc-fee")
public class OCFeesController {

    private final OCFeeService ocFeeService;

    @Autowired
    public OCFeesController(OCFeeService ocFeeService) {
        this.ocFeeService = ocFeeService;
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OCFee> getAllEntries() {
        return ocFeeService.getAllEntries();
    }

}
