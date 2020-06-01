package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "maintenance_txn")
@Entity
@Data
@Builder
public class MaintenanceTxn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "txn_id")
    protected Long txnId;
    @Column(name = "flat_number", nullable = false)
    private String flatNumber;
    @Column(name = "txn_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date txnDate;
    @Column(name = "month", nullable = false)
    private String month;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "actual_payment", nullable = false)
    private Double actualPayment;
    @Column(name = "payment_mode", nullable = false)
    private String paymentMode;
    @Column(name = "balance")
    private Double balance;

    @Override
    public String toString() {
        return "MaintenanceTxn{" +
                "flatNumber='" + flatNumber + '\'' +
                ", txnDate=" + txnDate +
                ", actualPayment=" + actualPayment +
                '}';
    }
}