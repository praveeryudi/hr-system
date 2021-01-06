package com.example.demo.service;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.dao.MaintenanceTxnDAO;
import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.entity.MaintenanceTxn;
import com.example.demo.pojo.ClearFromBalance;
import com.example.demo.request.TxnRequest;
import com.example.demo.response.TxnResponse;
import com.example.demo.util.MaintenanceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.util.MaintenanceUtil.getPreviousMonthYear;

@Service
@Slf4j
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
        maintenanceTxnDAO.saveAndFlush(maintenanceTxn);
        txnResponse.setMaintenanceTxn(maintenanceTxn);
        txnResponse.setInfoMessage("Record Saved !!");
        return txnResponse;
    }

    @Transactional
    public TxnResponse deleteTransactions(List<Long> txnIds) {
        for(Long txnId : txnIds) {
            maintenanceTxnDAO.deleteById(txnId);
        }
        TxnResponse txnResponse = new TxnResponse();
        txnResponse.setInfoMessage(txnIds.size() + " transactions deleted");
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

    public String clearFromBalance(ClearFromBalance clearFromBalance) {
        FlatMaintenanceLookUp flatData = getIndividualFlatData(clearFromBalance.getFlatNumber());
        Double expMaintenance = flatData.getExpectedMaintenance();
        String currentMonth = MaintenanceUtil.getMonthInString(Integer.valueOf(clearFromBalance.getMonth()) + 1);
        String[] prev = getPreviousMonthYear(currentMonth, clearFromBalance.getYear());
        MaintenanceTxn prevTxn = maintenanceTxnDAO.getTxn(prev[0],
                prev[1],
                clearFromBalance.getFlatNumber());
        if(null == prevTxn) {
            return "No Previous Txn Available.";
        }
        Double balance = prevTxn.getBalance();
        if(balance >= expMaintenance) {
            Double updatedBalance = balance - expMaintenance;
            MaintenanceTxn maintenanceTxn = MaintenanceTxn.builder()
                    .flatNumber(clearFromBalance.getFlatNumber())
                    .txnDate(new Date())
                    .month(clearFromBalance.getMonth())
                    .year(clearFromBalance.getYear())
                    .actualPayment(0.0)
                    .paymentMode("online")
                    .balance(updatedBalance)
                    .build();
            maintenanceTxnDAO.save(maintenanceTxn);
            return "Maintenance adjusted from balance, new balance = " + updatedBalance;
        }
        else {
            return "Insufficient / Zero Balance to clear dues!";
        }
    }

    public Map<String, Double> getFloorWiseTotal(String month, String year) {
        month = MaintenanceUtil.getMonthInString(Integer.valueOf(month));
        Map<String, Double> result = new LinkedHashMap<>();
        for(int floor = 0; floor <= 4; floor++) {
            Double total = maintenanceTxnDAO.getFloorWiseMaintenance(month, year, String.valueOf(floor));
            result.put(String.valueOf(floor), total);
        }
        log.info("Floor wise totals {}", result);
        return result;
    }
}
