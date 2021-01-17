package com.example.demo.controller;

import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.entity.MaintenanceTxn;
import com.example.demo.pojo.ClearFromBalance;
import com.example.demo.request.TxnRequest;
import com.example.demo.response.TxnResponse;
import com.example.demo.service.FlatMaintenanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/maintenance")
@Api(value = "maintenance", tags = "maintenance")
public class FlatMaintenanceController {

    private final FlatMaintenanceService flatMaintenanceService;

    @Autowired
    public FlatMaintenanceController(FlatMaintenanceService flatMaintenanceService) {
        this.flatMaintenanceService = flatMaintenanceService;
    }

    @GetMapping(value = "/lookup")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get Flat Master Data",
            notes = "Gets master data for all flats",
            response = FlatMaintenanceLookUp.class,
            responseContainer = "List",
            nickname = "getLookupData")
    public List<FlatMaintenanceLookUp> getAllFlatLookupData() {
        return flatMaintenanceService.getAllFlatData();
    }

    @GetMapping(value = "/flat/{flatNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FlatMaintenanceLookUp getIndividualFlatLookup(@PathVariable final String flatNumber) {
        return flatMaintenanceService.getIndividualFlatData(flatNumber);
    }

    @PostMapping(value = "/addMaintenance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public TxnResponse addTransaction(@RequestBody TxnRequest txnRequest) {
        return flatMaintenanceService.addMaintenanceTxn(txnRequest);
    }

    @DeleteMapping(value = "/deleteTxn", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TxnResponse deleteTransactions(@RequestBody List<Long> txnIds) {
        return flatMaintenanceService.deleteTransactions(txnIds);
    }

    @GetMapping(value = "/transactions/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Gets All Maintenance transactions",
            notes = "Gets All Maintenance transactions",
            response = MaintenanceTxn.class,
            responseContainer = "List",
            nickname = "getAllTransactions")
    public List<MaintenanceTxn> getAllTransactions() {
        return flatMaintenanceService.getAllTransactions();
    }

    @GetMapping(value = "/pending/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, List<FlatMaintenanceLookUp>> getPendingFlatsList(@PathVariable final String month,
                                                                        @PathVariable final String year) {
        return flatMaintenanceService.getPendingFlatsList(month, year);
    }

    @GetMapping(value = "/getBalances")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Double> getBalances() {
        return flatMaintenanceService.getBalances();
    }

    @PostMapping(value = "/adjustFromBalance")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Adjust maintenance from balance",
            notes = "Adjust maintenance from balance",
            response = ResponseEntity.class,
            nickname = "adjustFromBalance")
    public ResponseEntity<String> adjustFromBalance(@RequestBody ClearFromBalance clearFromBalance) {
        String response = flatMaintenanceService.clearFromBalance(clearFromBalance);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/floorTotal/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Gets floor wise totals",
            notes = "Gets floor wise totals",
            response = Map.class,
            nickname = "getFloorWiseTotal")
    public Map<String, Double> getFloorWiseTotal(@PathVariable final String month,
                                                 @PathVariable final String year) {
        return flatMaintenanceService.getFloorWiseTotal(month, year);
    }

}
