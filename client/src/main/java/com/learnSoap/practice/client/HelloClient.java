package com.learnSoap.practice.client;


import com.mgaidau.soappractice.courses.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.learnSoap.practice.client.service.+

import javax.xml.bind.JAXBElement;
import java.util.List;

@Slf4j
public class HelloClient {

    private final WebServiceTemplate webServiceTemplate;
    private final ObjectFactory objectFactory;

    public HelloClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
        objectFactory = new ObjectFactory();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(200);
        courseDetails.setName("debil");
        courseDetails.setDescription("motan");
//        log.warn("Status 1: {}", addCourseDetails(courseDetails));
//        log.warn("Status 2: {}", addCourseDetails(courseDetails));
        getAllCourseDetails().stream().
                forEach(course ->
                        log.info("element:\t{}\t{}\t{}", course.getId(),
                                course.getName(), course.getDescription()));
        log.warn("Deleted element id: {}", deleteCourseById(200));
        courseDetails = getCourseDetailsById(1);
        log.info("Get element by id :\t{}\t{}\t{}", courseDetails.getId(),
                courseDetails.getName(), courseDetails.getDescription());

    }



}
