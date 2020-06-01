package com.example.demo.controller;

import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.response.TxnResponse;
import com.example.demo.service.FlatMaintenanceLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.demo.request.TxnRequest;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/maintenance")
public class FlatMaintenanceController {

    private final FlatMaintenanceLookupService flatMaintenanceLookupService;

    @Autowired
    public FlatMaintenanceController(FlatMaintenanceLookupService flatMaintenanceLookupService) {
        this.flatMaintenanceLookupService = flatMaintenanceLookupService;
    }

    @GetMapping(value = "/lookup")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<FlatMaintenanceLookUp> getAllFlatLookupData() {
        return flatMaintenanceLookupService.getAllFlatData();
    }

    @PostMapping(value = "/addMaintenance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public TxnResponse addTransaction(@RequestBody TxnRequest txnRequest) {
        return flatMaintenanceLookupService.addMaintenanceTxn(txnRequest);
    }
}
