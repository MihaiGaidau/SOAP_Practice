package com.learnSoap.practice.client.controller;

import com.learnSoap.practice.client.service.EnrollService;
import com.learnSoap.practice.client.service.FingerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import se.snabbfoto.ejb.Photo;
import se.snabbfoto.ejb.Signature;

@Controller
public class EnrollController {
    private final EnrollService enrollService;
    private final FingerService fingerService;

    public EnrollController(EnrollService enrollService, FingerService fingerService) {
        this.enrollService = enrollService;
        this.fingerService = fingerService;
    }


    @GetMapping("/takePhoto")
    public ResponseEntity<Integer> takePhoto() {
       Integer response = enrollService.takePhoto();

//        log.info("init" + document);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/getPhoto")
    public ResponseEntity<Photo> getPhoto() {
        Photo response = enrollService.getPhoto();

//        log.info("init" + document);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/takeAndGetPhoto")
    public ResponseEntity<Photo> takeAndGetPhoto() {
        Photo response = enrollService.takeAndGetPhoto();

//        log.info("init" + document);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getSignature")
    public ResponseEntity<Signature> getSignature() {
        Signature response = enrollService.getSignature();

//        log.info("init" + document);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/captureSignature")
    public ResponseEntity<Boolean> captureSignature() {
        Boolean response = enrollService.captureSignature();

//        log.info("init" + document);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRawPhoto")
    public ResponseEntity<Photo> getRawPhoto() {
        Photo response = enrollService.getRawPhoto();

//        log.info("init" + document);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/takeFinger")
    public ResponseEntity<Integer> takeFinger() {
        Integer response = fingerService.captureFinger(0,1);

//        log.info("init" + document);

        return ResponseEntity.ok(response);
    }



}
