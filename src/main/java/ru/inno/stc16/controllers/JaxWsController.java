package ru.inno.stc16.controllers;

import ru.inno.stc16.entity.Course;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebService(name = "myWebService")
public class JaxWsController {

    private static Map<Integer, Course> courses = new ConcurrentHashMap<>();

    static {
        courses.put(0, new Course("Air Guitar"));
        courses.put(1, new Course("The pinball mastery"));
    }

    @WebMethod
    public Collection<Course> getCourses() {
        return courses.values();
    }

    @WebMethod
    public Course getCourse(Integer id) {
        return courses.get(id);
    }

    @WebMethod
    public Integer addCourse(Course course) {
        if (courses.containsKey(course.getId())) {
            return -1;
        } else {
            courses.put(course.getId(), course);
            return course.getId();
        }
    }

    @WebMethod
    public Course deleteCourse(Integer id) {
        if (courses.containsKey(id)) {
            return courses.remove(id);
        } else {
            return null;
        }
    }
}
