package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxnRequest implements Serializable {

    private Date txnDate;
    private String flatNumber;
    private String month;
    private Integer year;
    private Double actualPayment;
    private String paymentMode;
}
