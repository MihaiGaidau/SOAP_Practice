//package com.learnSoap.practice.client;
//
//
//import com.mgaidau.soappractice.courses.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.ws.client.core.WebServiceTemplate;
//import com.learnSoap.practice.client.service.+
//
//@Slf4j
//public class HelloClient {
//
//    private final WebServiceTemplate webServiceTemplate;
//    private final ObjectFactory objectFactory;
//    private final ClientService clientService;
//
//    public HelloClient(WebServiceTemplate webServiceTemplate, ClientService clientService) {
//        this.webServiceTemplate = webServiceTemplate;
//        this.clientService = clientService;
//        objectFactory = new ObjectFactory();
//        CourseDetails courseDetails = new CourseDetails();
//        courseDetails.setId(200);
//        courseDetails.setName("debil");
//        courseDetails.setDescription("motan");
////        log.warn("Status 1: {}", addCourseDetails(courseDetails));
////        log.warn("Status 2: {}", addCourseDetails(courseDetails));
//        clientService.getAllCourseDetails().stream().
//                forEach(course ->
//                        log.info("element:\t{}\t{}\t{}", course.getId(),
//                                course.getName(), course.getDescription()));
//        log.warn("Deleted element id: {}",clientService.deleteCourseById(200));
//        courseDetails = clientService.getCourseDetailsById(1);
//        log.info("Get element by id :\t{}\t{}\t{}", courseDetails.getId(),
//                courseDetails.getName(), courseDetails.getDescription());
//
//    }
//
//
//
//}
