package com.learnSoap.practice.client.service;

import com.mgaidau.soappractice.courses.*;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;

@Service
public class ClientService {

    private final WebServiceTemplate webServiceTemplate;
    private final ObjectFactory objectFactory;

    public ClientService(WebServiceTemplate webServiceTemplate, ObjectFactory objectFactory) {
        this.webServiceTemplate = webServiceTemplate;
        this.objectFactory = objectFactory;
    }

    @SuppressWarnings("unchecked")
    public List<CourseDetails> getAllCourseDetails() {

        GetAllCourseDetailsRequest request =
                objectFactory.createGetAllCourseDetailsRequest();

        GetAllCourseDetailsResponse response =
                (GetAllCourseDetailsResponse) webServiceTemplate
                        .marshalSendAndReceive(request);

        return response.getCourseDetails();
    }

    @SuppressWarnings("unchecked")
    public Status addCourseDetails(CourseDetails courseDetails) {
        AddCourseRequest request = objectFactory.createAddCourseRequest();
        request.setCourseDetails(courseDetails);
        AddCourseResponse response = (AddCourseResponse) webServiceTemplate.marshalSendAndReceive(request);
        return response.getStatus();
    }

    @SuppressWarnings("unchecked")
    public Status deleteCourseById(int id) {
        DeleteCourseByIdRequest request = objectFactory.createDeleteCourseByIdRequest();
        request.setId(id);
        DeleteCourseByIdResponse response = (DeleteCourseByIdResponse) webServiceTemplate.marshalSendAndReceive(request);
        return response.getStatus();
    }

    @SuppressWarnings("unchecked")
    public CourseDetails getCourseDetailsById(int id) {
        GetCourseDetailsRequest request = objectFactory.createGetCourseDetailsRequest();
        request.setId(id);
        GetCourseDetailsResponse response = (GetCourseDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
        return response.getCourseDetails();
    }
}
