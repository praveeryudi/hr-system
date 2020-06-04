package com.example.demo.dao;

import com.example.demo.entity.FlatMaintenanceLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatMaintenanceLookUpDAO extends JpaRepository<FlatMaintenanceLookUp, String> {

    @Query(value = "SELECT ml from FlatMaintenanceLookUp ml where ml.flatNumber = ?1")
    FlatMaintenanceLookUp getExpectedPaymentByFlatNumber(String flatNumber);

    @Query(value = "select * from flat_maintenance_lookup ml where ml.flat_number NOT IN (select mt.flat_number from maintenance_txn mt where mt.month = ?1 and mt.year = ?2)", nativeQuery = true)
    List<FlatMaintenanceLookUp> getPendingFlats(String month, String year);
}
