package com.example.demo.service;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.entity.FlatMaintenanceLookUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatMaintenanceLookupService {

    private final FlatMaintenanceLookUpDAO flatMaintenanceLookUpDAO;

    @Autowired
    public FlatMaintenanceLookupService(FlatMaintenanceLookUpDAO flatMaintenanceLookUpDAO) {
        this.flatMaintenanceLookUpDAO = flatMaintenanceLookUpDAO;
    }

    public List<FlatMaintenanceLookUp> getAllFlatData() {
        return flatMaintenanceLookUpDAO.findAll();
    }
}
