package com.example.JobsInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class JobsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobsController.class);

    @Value("${server.port}")
    private String port;


    @RequestMapping("/")
    public JList getJobsList(){
        LOGGER.info("get the jobs list ");
        List<Jobs> jobses= Arrays.asList(
                new Jobs(1, "IT", 120000),
                new Jobs(2, "Pilot", 246000),
                new Jobs(3, "Teaching", 38000),
                new Jobs(4, "Marketing", 205300),
                new Jobs(5, "Accounting", 2099900)
        );
        JList jobsList = new JList();
        jobsList.setJobsList(jobses);
        return jobsList;
    }
    @GetMapping("/ping")
    public String ping()
    {
        LOGGER.info("get port number ");
        return "I'm in " + port;
    }

}
