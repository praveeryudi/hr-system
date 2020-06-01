package com.example.demo.dao;

import com.example.demo.entity.FlatMaintenanceLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatMaintenanceLookUpDAO extends JpaRepository<FlatMaintenanceLookUp, String> {

    @Query(value = "SELECT ml from FlatMaintenanceLookUp ml where ml.flatNumber = ?1")
    FlatMaintenanceLookUp getExpectedPaymentByFlatNumber(String flatNumber);
}
