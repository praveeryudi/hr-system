package com.example.demo.dao;

import com.example.demo.entity.MaintenanceTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceTxnDAO extends JpaRepository<MaintenanceTxn, Long> {

    @Query(value = "SELECT mt from MaintenanceTxn mt where mt.month = ?1 AND mt.year = ?2 AND mt.flatNumber = ?3")
    MaintenanceTxn getTxn(String previousMonth, String year, String flatNumber);

    @Query(value = "select sum(actual_payment) from maintenance_txn where month = :month and year = :year and flat_number like :floor%", nativeQuery = true)
    Double getGroundFloorMaintenance(@Param(value = "month") String month,
                                     @Param(value = "year") String year,
                                     @Param(value = "floor") String floor);

}
