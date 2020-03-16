package com.learnSoap.practice.client.service;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import se.snabbfoto.ejb.CaptureFingerprints;
import se.snabbfoto.ejb.CaptureFingerprintsResponse;
import se.snabbfoto.ejb.ObjectFactory;

@Service
public class FingerService {
    private final ObjectFactory objectFactory;
    private final WebServiceTemplate webServiceTemplate2;

    public FingerService(WebServiceTemplate webServiceTemplate2) {
        this.webServiceTemplate2 = webServiceTemplate2;
        objectFactory = new ObjectFactory();
    }


    public int captureFinger(int left, int right) {
        CaptureFingerprints captureFingerprints = objectFactory.createCaptureFingerprints();
        captureFingerprints.setArg0((byte) left);
        captureFingerprints.setArg1((byte) right);
        CaptureFingerprintsResponse response =
                (CaptureFingerprintsResponse) webServiceTemplate2.marshalSendAndReceive(captureFingerprints);
        return response.getReturn();

    }
}
