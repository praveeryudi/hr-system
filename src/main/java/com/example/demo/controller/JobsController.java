package com.example.demo.controller;

import com.example.demo.entity.Jobs;
import com.example.demo.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobsController {

    private final JobsService jobsService;

    @Autowired
    public JobsController(JobsService jobsService) {
        this.jobsService = jobsService;
    }

    @GetMapping(value = "/jobs/all")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Jobs> listAllJobs() {
        return jobsService.getAllJobs();
    }

}