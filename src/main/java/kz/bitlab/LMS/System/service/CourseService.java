// File: service/CourseService.java
package kz.bitlab.LMS.System.service;

import kz.bitlab.LMS.System.exception.CourseNotFoundException;
import kz.bitlab.LMS.System.model.Course;
import kz.bitlab.LMS.System.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findByIdOptional(Long id) {
        return courseRepository.findById(id);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    public List<Course> findByNameContaining(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }

    public void delete(Course course) {
        courseRepository.delete(course);
    }

    public long count() {
        return courseRepository.count();
    }
}