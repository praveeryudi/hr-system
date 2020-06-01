package com.example.demo.service;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.dao.MaintenanceTxnDAO;
import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.entity.MaintenanceTxn;
import com.example.demo.request.TxnRequest;
import com.example.demo.response.TxnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
        return maintenanceTxnDAO.findAll();
    }

    @Transactional
    public TxnResponse addMaintenanceTxn(TxnRequest txnRequest) {
        String flatNumber = txnRequest.getFlatNumber();
        Date txnDate = txnRequest.getTxnDate();
        String currentMonth = txnRequest.getMonth();
        String currentYear = txnRequest.getYear();
        Double actualPayment = txnRequest.getActualPayment();
        String paymentMode = txnRequest.getPaymentMode();

        // Compute Balance
        FlatMaintenanceLookUp flatData = flatMaintenanceLookUpDAO.getExpectedPaymentByFlatNumber(flatNumber);
        Double expectedPayment = flatData.getExpectedMaintenance();

        String[] previousTime = getPreviousMonthYear(currentMonth, currentYear);
        MaintenanceTxn previousTxn = maintenanceTxnDAO.getTxn(previousTime[0], previousTime[1], flatNumber);
        Double previousBalance = 0.0;
        if(null != previousTxn) {
            previousBalance = previousTxn.getBalance();
        }
        Double balance = actualPayment - expectedPayment + previousBalance;
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
        maintenanceTxn = maintenanceTxnDAO.save(maintenanceTxn);

        TxnResponse txnResponse = new TxnResponse();
        txnResponse.setMaintenanceTxn(maintenanceTxn);
        return txnResponse;
    }

    private String[] getPreviousMonthYear(String currentMonth, String currentYear) {
        String previousMonth;
        String previousYear = currentYear;
        switch(currentMonth) {
            case "JAN":
                previousMonth = "DEC";
                previousYear = String.valueOf(Integer.valueOf(currentYear) - 1);
                break;
            case "FEB":
                previousMonth = "JAN";
                break;
            case "MAR":
                previousMonth = "FEB";
                break;
            case "APR":
                previousMonth = "MAR";
                break;
            case "MAY":
                previousMonth = "APR";
                break;
            case "JUN":
                previousMonth = "MAY";
                break;
            case "JUL":
                previousMonth = "JUN";
                break;
            case "AUG":
                previousMonth = "JUL";
                break;
            case "SEP":
                previousMonth = "AUG";
                break;
            case "OCT":
                previousMonth = "SEP";
                break;
            case "NOV":
                previousMonth = "OCT";
                break;
            case "DEC":
                previousMonth = "NOV";
                break;
            default:
                previousMonth = currentMonth;
                break;
        }
        return new String[] {previousMonth, previousYear};
    }

    public void deleteTransaction(TxnRequest txnRequest) {
        MaintenanceTxn txn = maintenanceTxnDAO.getTxn(txnRequest.getMonth(), txnRequest.getYear(), txnRequest.getFlatNumber());
        maintenanceTxnDAO.delete(txn);
    }
}
