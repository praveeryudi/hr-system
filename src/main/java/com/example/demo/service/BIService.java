package com.example.demo.service;

import com.example.demo.dao.MaintenanceTxnDAO;
import com.example.demo.dao.MonthYearRepository;
import com.example.demo.entity.MonthYear;
import com.example.demo.pojo.PaymentSum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BIService {

    private final MonthYearRepository monthYearRepository;
    private final MaintenanceTxnDAO maintenanceTxnDAO;

    @Autowired
    public BIService(MonthYearRepository monthYearRepository,
                     MaintenanceTxnDAO maintenanceTxnDAO) {
        this.monthYearRepository = monthYearRepository;
        this.maintenanceTxnDAO = maintenanceTxnDAO;
    }

    public Map<String, Double> getSelectedDurationData(String timePeriod) {
        List<MonthYear> monthYearList = monthYearRepository.getMonthsList(timePeriod);
        log.info("Month Years Retrieved = {}", monthYearList);
        List<String> allMonths = monthYearList.stream().map(MonthYear::getMonth).collect(Collectors.toList());
        Set<String> allYears = monthYearList.stream().map(MonthYear::getYear).collect(Collectors.toSet());
        List<PaymentSum> totalPayments = maintenanceTxnDAO.sumActualPaymentByMonthYear(allMonths, allYears);
        log.info("{}", totalPayments);
        final Map<String, Double> aggregatePayments = new LinkedHashMap<>();
        aggregatePayments.put("DurationTotal", totalPayments.stream().mapToDouble(PaymentSum::getPaymentTotal).sum());
        totalPayments.forEach(agg -> {
            String key = agg.getMonth() + "," + agg.getYear();
            aggregatePayments.put(key, agg.getPaymentTotal());
        });

        return aggregatePayments;
    }

}
