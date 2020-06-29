package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "oc_fee")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OCFee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fee_id")
    protected Long feeId;
    @Column(name = "flat_number", nullable = false)
    private String flatNumber;
    @Column(name = "actual_payment", nullable = false)
    private Double actualPayment;
    @Column(name = "payment_mode", nullable = false)
    private String paymentMode;

}
