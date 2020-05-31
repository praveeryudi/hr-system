package com.example.demo.controller;

import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.service.FlatMaintenanceLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
