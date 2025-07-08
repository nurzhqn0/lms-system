// File: mapper/CourseMapper.java
package kz.bitlab.LMS.System.mapper;

import kz.bitlab.LMS.System.dto.request.CourseRequest;
import kz.bitlab.LMS.System.dto.response.CourseResponse;
import kz.bitlab.LMS.System.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request) {
        if (request == null) {
            return null;
        }

        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        return course;
    }

    public CourseResponse toResponse(Course course) {
        if (course == null) {
            return null;
        }

        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setName(course.getName());
        response.setDescription(course.getDescription());
        response.setCreatedTime(course.getCreatedTime());
        response.setUpdatedTime(course.getUpdatedTime());
        return response;
    }

    public void updateEntity(Course course, CourseRequest request) {
        if (course == null || request == null) {
            return;
        }

        course.setName(request.getName());
        course.setDescription(request.getDescription());
    }

    public List<CourseResponse> toResponseList(List<Course> courses) {
        if (courses == null) {
            return null;
        }

        return courses.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<Course> toEntityList(List<CourseRequest> requests) {
        if (requests == null) {
            return null;
        }

        return requests.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}