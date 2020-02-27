package com.learnSOAP.practice.firstExample.soap;

import com.learnSOAP.practice.firstExample.bean.Course;
import com.learnSOAP.practice.firstExample.service.CourseDetailsService;
import com.mgaidau.soappractice.courses.CourseDetails;
import com.mgaidau.soappractice.courses.GetCourseDetailsRequest;
import com.mgaidau.soappractice.courses.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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




}
