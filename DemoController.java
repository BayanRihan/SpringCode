package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

        @PostMapping("/add")
    public String addCustomer(@RequestParam String first, @RequestParam String last, @RequestParam String qual) {
            LOGGER.info("calling add cv  ");
        User user = new User();
        user.setFirstName(first);
        user.setLastName(last);
        user.setQualifications(qual);
        userRepository.save(user);
            LOGGER.info("Adding new cv => done  ");
        return "Done";

    }

//  return restTemplate.exchange("http://localhost:8083/", HttpMethod.GET , String.class).getBody();


    @HystrixCommand(fallbackMethod = "FallbackJ"
    ,commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
                            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")})
    @GetMapping("/jo")
    public List<Object> getJobs() {
        LOGGER.info("Before calling jobs-info-service ");
        String url = "http://localhost:8083/";
        Object[] jobs = restTemplate.getForObject(url, Object[].class);
        LOGGER.info("After calling jobs-info-service ");
        return Arrays.asList(jobs);
    }

     public List<Object> FallbackJ()
     {
         LOGGER.info("Calling jobs info service failed ");
         return Arrays.asList(new Jobs(1,"No Jobs",0));
     }

    @GetMapping("/find/{id}")
    public User findCustomerById(@PathVariable Integer id) {
        return userRepository.findUserById(id);
    }


}
