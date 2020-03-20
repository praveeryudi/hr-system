package com.example.demo.service;

import com.example.demo.dao.JobsRepository;
import com.example.demo.entity.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsService {

    private final JobsRepository jobsRepository;

    @Autowired
    public JobsService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public List<Jobs> getAllJobs() {
        return jobsRepository.findAll();
    }
}