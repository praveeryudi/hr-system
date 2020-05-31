package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flat_maintenance_lookup")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlatMaintenanceLookUp {

    @Id
    @Column(name = "flat_number", nullable = false)
    private String flatNumber;
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    @Column(name = "expected_maintenance", nullable = false)
    private Double expectedMaintenance;

}
