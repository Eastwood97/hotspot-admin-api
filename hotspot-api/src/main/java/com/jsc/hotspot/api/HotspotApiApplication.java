package com.jsc.hotspot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jsc.hotspot.api"})
public class HotspotApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotspotApiApplication.class, args);
    }

}
