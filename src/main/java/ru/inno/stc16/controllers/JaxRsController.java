package ru.inno.stc16.controllers;

import ru.inno.stc16.entity.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Path("/api/rs/courses")
public class JaxRsController {

    private static Map<Integer, Course> courses = new ConcurrentHashMap<>();

    static {
        courses.put(0, new Course("Air Guitar"));
        courses.put(1, new Course("The pinball mastery"));
    }

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses() throws Exception {
        if (courses.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(courses.values()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourse(@PathParam("id") Integer id) throws Exception {
        if (courses.containsKey(id)) {
            return Response.ok(courses.get(id)).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourse(Course course) throws Exception {
        if (courses.containsKey(course.getId())) {
            return Response.notModified().build();
        }
        courses.put(course.getId(), course);
        return Response.created(UriBuilder.fromUri(uriInfo.getAbsolutePath()).path("/{id}").build(course.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCourse(@PathParam("id") Integer id) throws Exception {
        if (!courses.containsKey(id)) {
            return Response.noContent().build();
        }
        return Response.ok(courses.remove(id)).build();
    }
}
