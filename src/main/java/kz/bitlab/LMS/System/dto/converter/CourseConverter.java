package kz.bitlab.LMS.System.dto.converter;

import kz.bitlab.LMS.System.dto.request.CourseRequest;
import kz.bitlab.LMS.System.dto.response.CourseResponse;
import kz.bitlab.LMS.System.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseConverter {

    public Course toEntity(CourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        return course;
    }

    public void updateEntity(Course course, CourseRequest request) {
        course.setName(request.getName());
        course.setDescription(request.getDescription());
    }

    public CourseResponse toResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setName(course.getName());
        response.setDescription(course.getDescription());
        response.setCreatedTime(course.getCreatedTime());
        response.setUpdatedTime(course.getUpdatedTime());
        return response;
    }

    public List<CourseResponse> toResponseList(List<Course> courses) {
        return courses.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}