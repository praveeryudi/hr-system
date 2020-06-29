package com.example.demo.service;

import com.example.demo.dao.OCFeeDAO;
import com.example.demo.entity.OCFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OCFeeService {

    private final OCFeeDAO ocFeeDAO;

    @Autowired
    public OCFeeService(OCFeeDAO ocFeeDAO) {
        this.ocFeeDAO = ocFeeDAO;
    }

    public List<OCFee> getAllEntries() {
        return ocFeeDAO.findAll(Sort.by(Sort.Direction.ASC, "flatNumber"));
    }
}
