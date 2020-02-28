package com.learnSOAP.practice.firstExample.soap;

import com.learnSOAP.practice.firstExample.bean.Course;
import com.learnSOAP.practice.firstExample.service.CourseDetailsService;
import com.mgaidau.soappractice.courses.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {
    private final CourseDetailsService courseDetailsService;

    public CourseDetailsEndpoint(CourseDetailsService courseDetailsService) {
        this.courseDetailsService = courseDetailsService;
    }

    @PayloadRoot(localPart = "GetCourseDetailsRequest", namespace = "http://mgaidau.com/SOAPpractice/courses")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){

        Course course = courseDetailsService.findById(request.getId());

        return mapCourseDetails(course);
    }

    @PayloadRoot(localPart = "GetAllCourseDetailsRequest", namespace = "http://mgaidau.com/SOAPpractice/courses")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request){
        List<Course> courses = courseDetailsService.findAll();

        return mapAllCourseDetails(courses);
    }


    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    private GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        List<CourseDetails> courseDetails = new ArrayList<>();
        for (Course course:courses){
            courseDetails.add(mapCourse(course));
        }
        response.getCourseDetails().addAll(courseDetails);
        return response;
    }

    @PayloadRoot(localPart = "DeleteCourseByIdRequest", namespace = "http://mgaidau.com/SOAPpractice/courses")
    @ResponsePayload
    public DeleteCourseByIdResponse deleteCourseByIdRequest(@RequestPayload DeleteCourseByIdRequest request){
        DeleteCourseByIdResponse response = new DeleteCourseByIdResponse();
        response.setDeleted(courseDetailsService.deleteById(request.getId()));
        return response;
    }




}
