//package com.learnSoap.practice.client.soap;
//
//
//import com.exception.CourseNotFoundException;
//import com.gzaharia.soappractice.*;
//import com.learnSoap.practice.client.bean.Course;
//import com.learnSoap.practice.client.service.CourseDetailsService;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Endpoint
//public class CourseDetailsEndpoint {
//    //method
//    //input - GetCourseDetailsRequest
//    //output - GetCourseDetailsResponse
//
//
//    private final CourseDetailsService service;
//
//    public CourseDetailsEndpoint(CourseDetailsService service) {
//        this.service = service;
//    }
//
//    @PayloadRoot(localPart = "GetCourseDetailsRequest", namespace = "http://gzaharia.com/SOAPpractice")
//    @ResponsePayload
//    public GetCourseDetailsResponse processCourseDetailsRequest
//            (@RequestPayload GetCourseDetailsRequest request) {
//        Course course = service.findById(request.getId());
//
//        if(course==null)
//            throw new CourseNotFoundException("Invalid course id: " + request.getId());
//
//        return mapCourseDetails(course);
//
//    }
//
//    //Get all course details request
//    @PayloadRoot(localPart = "GetAllCourseDetailsRequest", namespace = "http://gzaharia.com/SOAPpractice")
//    @ResponsePayload
//    public GetAllCourseDetailsResponse processAllCourseDetailsRequest
//    (@RequestPayload GetAllCourseDetailsRequest request) {
//
//        //Domain object
//        List<Course> courseList = service.findAll();
//
//        //DTO
//        List<CourseDetails> courseDetails = new ArrayList<>();
//
//        //Response object
//        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
//
//        for (Course course : courseList
//        ) {
//            courseDetails.add(mapCourse(course));
//        }
//        response.getCourseDetails().addAll(courseDetails);
//
//
//        return response;
//    }
//
//
//    private CourseDetails mapCourse(Course course) {
//        CourseDetails courseDetails = new CourseDetails();
//        courseDetails.setId(course.getId());
//        courseDetails.setName(course.getName());
//        courseDetails.setDescription(course.getDescription());
//        return courseDetails;
//    }
//
//    private GetCourseDetailsResponse mapCourseDetails(Course course) {
//        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
//        response.setCourseDetails(mapCourse(course));
//        return response;
//    }
//
//    @PayloadRoot(localPart = "DeleteCourseByIdRequest", namespace = "http://gzaharia.com/SOAPpractice")
//    @ResponsePayload
//    public DeleteCourseByIdResponse processCourseDetailsRequest
//            (@RequestPayload DeleteCourseByIdRequest request) {
//        boolean deletedElement = service.deleteById(request.getId());
//
//        DeleteCourseByIdResponse response = new DeleteCourseByIdResponse();
//        response.setDeleted(deletedElement);
//
//        return response;
//
//    }
//
//    @PayloadRoot(localPart = "AddCourseByIdRequest", namespace = "http://gzaharia.com/SOAPpractice")
//    @ResponsePayload
//    public AddCourseByIdResponse processCourseDetailsRequest
//            (@RequestPayload AddCourseByIdRequest request) {
//
//        Course course = Course
//                .builder()
//                .id(request.getCourseDetails().getId())
//                .name(request.getCourseDetails().getName())
//                .description(request.getCourseDetails().getDescription())
//                .build();
//
//       Status addedElement = service.addCourse(course);
//
//        AddCourseByIdResponse response = new AddCourseByIdResponse();
//        response.setStatus(mapStatus(addedElement));
//
//        return response;
//
//    }
//
//    private Status mapStatus(Status status) {
//        if (status == Status.FAILURE)
//            return Status.FAILURE;
//
//        return Status.SUCCESS;
//    }
//
//
//}