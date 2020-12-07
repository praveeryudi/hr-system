package com.example.demo.dao;

import com.example.demo.entity.MaintenanceTxn;
import com.example.demo.pojo.PaymentSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MaintenanceTxnDAO extends JpaRepository<MaintenanceTxn, Long> {

    @Query(value = "SELECT mt from MaintenanceTxn mt where mt.month = ?1 AND mt.year = ?2 AND mt.flatNumber = ?3")
    MaintenanceTxn getTxn(String previousMonth, String year, String flatNumber);

    @Query(value = "select sum(actual_payment) from maintenance_txn where month = :month and year = :year and flat_number like CONCAT(:floor,'%')", nativeQuery = true)
    Double getFloorWiseMaintenance(@Param(value = "month") String month,
                                   @Param(value = "year") String year,
                                   @Param(value = "floor") String floor);

    @Query(value = "SELECT new com.example.demo.pojo.PaymentSum(mt.month, mt.year, sum(mt.actualPayment)) from MaintenanceTxn mt group by mt.month, mt.year having mt.month IN (?1) and mt.year IN (?2)")
    List<PaymentSum> sumActualPaymentByMonthYear(List<String> months, Set<String> years);

}
