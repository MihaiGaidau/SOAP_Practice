package com.learnSoap.practice.client.controller;

import com.learnSoap.practice.client.service.EnrollService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnrollController {
    private final EnrollService enrollService;

    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }


    @GetMapping("/scan")
    public ResponseEntity<Integer> viewData() {
       Integer response = enrollService.takePhoto();

//        log.info("init" + document);

        return ResponseEntity.ok(response);
    }

}
