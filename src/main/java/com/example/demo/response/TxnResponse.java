package com.example.demo.response;

import com.example.demo.entity.MaintenanceTxn;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxnResponse {

    private MaintenanceTxn maintenanceTxn;
}
