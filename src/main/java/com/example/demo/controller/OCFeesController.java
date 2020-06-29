package com.example.demo.controller;

import com.example.demo.entity.OCFee;
import com.example.demo.service.OCFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<OCFee> getAllEntries() {
        return ocFeeService.getAllEntries();
    }

}
