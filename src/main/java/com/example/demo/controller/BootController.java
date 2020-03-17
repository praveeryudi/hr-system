package com.example.demo.controller;

import com.example.demo.annotation.LogExecutionTime;
import com.example.demo.annotation.LogMethodExecution;
import com.example.demo.entity.PostDO;
import com.example.demo.exception.PostRetrievalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BootController {

    @GetMapping(value = "/get/post")
    @LogExecutionTime
    @LogMethodExecution
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PostDO getPostDetailsForPost() {
        PostDO postDO = new PostDO();
        postDO.setId(10);
        postDO.setTitle("My New Post");
        postDO.setBody("This is the body");
        return postDO;
    }
}
