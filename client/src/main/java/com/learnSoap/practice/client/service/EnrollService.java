package com.learnSoap.practice.client.service;

import org.springframework.stereotype.Service;
import se.snabbfoto.ejb.*;
import org.springframework.ws.client.core.WebServiceTemplate;


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

    @SuppressWarnings("unchecked")
    public Photo getPhoto(){
        GetPhoto getPhotoRequest = objectFactory.createGetPhoto();
        GetPhotoResponse response = (GetPhotoResponse) webServiceTemplate.marshalSendAndReceive(getPhotoRequest);
        return response.getReturn();
    }

    @SuppressWarnings("unchecked")
    public Photo takeAndGetPhoto(){
        Integer numar = takePhoto();
        Photo photo = getPhoto();
        showPhoto(photo);
        return photo;
    }

    @SuppressWarnings("unchecked")
    public void showPhoto(Photo photo){
        ShowPhoto showPhoto = objectFactory.createShowPhoto();
        showPhoto.setArg0(photo);
        ShowPhotoResponse response = (ShowPhotoResponse) webServiceTemplate.marshalSendAndReceive(showPhoto);
//        return response;
    }

    @SuppressWarnings("unchecked")

    public boolean captureSignature(){
        CaptureSignature captureSignature = objectFactory.createCaptureSignature();
        CaptureSignatureResponse captureSignatureResponse =
                (CaptureSignatureResponse) webServiceTemplate.marshalSendAndReceive(captureSignature);
        return captureSignatureResponse.isReturn();
    }

    @SuppressWarnings("unchecked")

    public Signature getSignature(){
            GetSignature getSignature = objectFactory.createGetSignature();
            GetSignatureResponse getSignatureResponse =
                    (GetSignatureResponse) webServiceTemplate.marshalSendAndReceive(getSignature);
            return getSignatureResponse.getReturn();

    }

    @SuppressWarnings("unchecked")

    public Photo getRawPhoto(){
        takePhoto();
        GetRawPhoto getRawPhoto = objectFactory.createGetRawPhoto();
        GetRawPhotoResponse getRawPhotoResponse = (GetRawPhotoResponse) webServiceTemplate.marshalSendAndReceive(getRawPhoto);
        return getRawPhotoResponse.getReturn();
    }
}
