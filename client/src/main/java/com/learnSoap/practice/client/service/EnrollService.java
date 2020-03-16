package com.learnSoap.practice.client.service;

import org.springframework.stereotype.Service;
import se.snabbfoto.ejb.CapturePhoto;
import se.snabbfoto.ejb.CapturePhotoResponse;
import org.springframework.ws.client.core.WebServiceTemplate;
import se.snabbfoto.ejb.ObjectFactory;


@Service
public class EnrollService {
    private final WebServiceTemplate webServiceTemplate;
    private ObjectFactory objectFactory;

    public EnrollService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
        objectFactory = new ObjectFactory();
    }

    @SuppressWarnings("unchecked")
    public int takePhoto(){
        CapturePhoto capturePhoto = objectFactory.createCapturePhoto();
//        webServiceTemplate


        CapturePhotoResponse response = (CapturePhotoResponse)  webServiceTemplate.marshalSendAndReceive(capturePhoto);
        return response.getReturn();
    }

}
