package com.learnSOAP.practice.firstExample.soap;

import com.mgaidau.soappractice.courses.CourseDetails;
import com.mgaidau.soappractice.courses.GetCourseDetailsRequest;
import com.mgaidau.soappractice.courses.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    @PayloadRoot(localPart = "GetCourseDetailsRequest", namespace = "http://mgaidau.com/SOAPpractice/courses")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){
       GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(request.getId());
        courseDetails.setName("Microservices Course");
        courseDetails.setDescription("good course");
       return response;
    }




}
