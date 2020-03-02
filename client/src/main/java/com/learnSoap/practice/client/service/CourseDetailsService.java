package com.learnSoap.practice.client.service;


import com.learnSoap.practice.client.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {


    private static List<Course> courses = new ArrayList<>();

    static {
        Course course1 = new Course(1, "Sptring", "10 steps");
        courses.add(course1);

        Course course2 = new Course(2, "Mihai", "10 steps");
        courses.add(course2);

        Course course3 = new Course(3, "Vasea", "10 steps");
        courses.add(course3);

        Course course4 = new Course(4, "Sergiu", "10 steps");
        courses.add(course4);

        Course course5 = new Course(5, "Vova", "10 steps");
        courses.add(course5);
    }

    public Course findById(int id) {
        for (Course course : courses
        ) {
            if (course.getId() == id)
                return course;

        }
        return null;
    }

    public List<Course> findAll() {
        return courses;
    }

    public boolean deleteById(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            {
                if (course.getId() == id) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

//    public Status addCourse(Course course){
//        if(existCourse(course))
//            return Status.FAILURE;
//        courses.add(course);
//        return Status.SUCCESS;
//    }

    public boolean existCourse(Course course){
        return findById(course.getId()) != null;
    }
}
