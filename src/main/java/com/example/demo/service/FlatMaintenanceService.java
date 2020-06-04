package com.example.demo.service;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.dao.MaintenanceTxnDAO;
import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.entity.MaintenanceTxn;
import com.example.demo.request.TxnRequest;
import com.example.demo.response.TxnResponse;
import com.example.demo.util.MaintenanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlatMaintenanceService {

    private final FlatMaintenanceLookUpDAO flatMaintenanceLookUpDAO;
    private final MaintenanceTxnDAO maintenanceTxnDAO;

    @Autowired
    public FlatMaintenanceService(FlatMaintenanceLookUpDAO flatMaintenanceLookUpDAO,
                                  MaintenanceTxnDAO maintenanceTxnDAO) {
        this.flatMaintenanceLookUpDAO = flatMaintenanceLookUpDAO;
        this.maintenanceTxnDAO = maintenanceTxnDAO;
    }

    public List<FlatMaintenanceLookUp> getAllFlatData() {
        return flatMaintenanceLookUpDAO.findAll();
    }

    public List<MaintenanceTxn> getAllTransactions() {
        return maintenanceTxnDAO.findAll(Sort.by(Sort.Direction.ASC, "flatNumber"));
    }

    @Transactional
    public TxnResponse addMaintenanceTxn(TxnRequest txnRequest) {
        TxnResponse txnResponse = new TxnResponse();
        String flatNumber = txnRequest.getFlatNumber();
        Date txnDate = txnRequest.getTxnDate();
        String currentMonth = txnRequest.getSelectedMonth();
        String currentYear = txnRequest.getSelectedYear();
        Double expectedMaintenance = txnRequest.getExpectedMaintenance();
        Double actualPayment = txnRequest.getActualPayment();
        String paymentMode = txnRequest.getPaymentMode();

        // Compute Balance
        currentMonth = MaintenanceUtil.getMonthInString(Integer.valueOf(currentMonth) + 1);
        // Check if entry for current month already exists
        MaintenanceTxn txn = maintenanceTxnDAO.getTxn(currentMonth, currentYear, flatNumber);
        if(null != txn) {
            txnResponse.setMaintenanceTxn(txn);
            txnResponse.setInfoMessage("Entry already exists !!");
            return txnResponse;
        }

        String[] previousTime = MaintenanceUtil.getPreviousMonthYear(currentMonth, currentYear);
        MaintenanceTxn previousTxn = maintenanceTxnDAO.getTxn(previousTime[0], previousTime[1], flatNumber);
        Double previousBalance = 0.0;
        if(null != previousTxn) {
            previousBalance = previousTxn.getBalance();
        }
        Double balance = actualPayment - expectedMaintenance + previousBalance;
        MaintenanceTxn maintenanceTxn = MaintenanceTxn.builder()
                .flatNumber(flatNumber)
                .txnDate(txnDate)
                .month(currentMonth)
                .year(currentYear)
                .actualPayment(actualPayment)
                .paymentMode(paymentMode)
                .balance(balance)
                .build();

        // Insert the new txn
        maintenanceTxnDAO.save(maintenanceTxn);
        txnResponse.setMaintenanceTxn(maintenanceTxn);
        return txnResponse;
    }

    @Transactional
    public TxnResponse deleteTransaction(TxnRequest txnRequest) {
        MaintenanceTxn txn = maintenanceTxnDAO.getTxn(txnRequest.getSelectedMonth(), txnRequest.getSelectedYear(), txnRequest.getFlatNumber());
        maintenanceTxnDAO.delete(txn);
        TxnResponse txnResponse = new TxnResponse();
        txnResponse.setMaintenanceTxn(txn);
        return txnResponse;
    }

    public FlatMaintenanceLookUp getIndividualFlatData(String flatNumber) {
        Optional<FlatMaintenanceLookUp> flatDataOptional = flatMaintenanceLookUpDAO.findById(flatNumber);
        flatDataOptional.orElseThrow(RuntimeException::new);
        return flatDataOptional.get();
    }

    public Map<String, List<FlatMaintenanceLookUp>> getPendingFlatsList(String month, String year) {
        month = MaintenanceUtil.getMonthInString(Integer.valueOf(month));
        List<FlatMaintenanceLookUp> pendingFlats = flatMaintenanceLookUpDAO.getPendingFlats(month, year);
        Map<String, List<FlatMaintenanceLookUp>> result = new HashMap<>();
        List<FlatMaintenanceLookUp> groundFloor = pendingFlats.stream().filter(flat -> flat.getFlatNumber().startsWith("0")).collect(Collectors.toList());
        List<FlatMaintenanceLookUp> firstFloor = pendingFlats.stream().filter(flat -> flat.getFlatNumber().startsWith("1")).collect(Collectors.toList());
        List<FlatMaintenanceLookUp> secondFloor = pendingFlats.stream().filter(flat -> flat.getFlatNumber().startsWith("2")).collect(Collectors.toList());
        List<FlatMaintenanceLookUp> thirdFloor = pendingFlats.stream().filter(flat -> flat.getFlatNumber().startsWith("3")).collect(Collectors.toList());
        List<FlatMaintenanceLookUp> fourthFloor = pendingFlats.stream().filter(flat -> flat.getFlatNumber().startsWith("4")).collect(Collectors.toList());
        result.put("0", groundFloor);
        result.put("1", firstFloor);
        result.put("2", secondFloor);
        result.put("3", thirdFloor);
        result.put("4", fourthFloor);
        return result;
    }
}
