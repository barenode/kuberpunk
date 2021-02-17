package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/sample")
public class SampleController {

    @RequestMapping(value="/{uuid}", method = RequestMethod.GET)
    public Sample test(@PathVariable("uuid") int uuid) {
        return new Sample(uuid);
    }
}
