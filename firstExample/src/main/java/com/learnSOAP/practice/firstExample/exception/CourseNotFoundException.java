package com.learnSOAP.practice.firstExample.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

import javax.wsdl.Fault;

@SoapFault(faultCode = FaultCode.CLIENT )
public class  CourseNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3518170101751491969L;
    public CourseNotFoundException(String message) {
        super(message);
    }
}
