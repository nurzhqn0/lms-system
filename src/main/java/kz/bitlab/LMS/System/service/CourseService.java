package kz.bitlab.LMS.System.service;

import kz.bitlab.LMS.System.exception.CourseNotFoundException;
import kz.bitlab.LMS.System.model.Course;
import kz.bitlab.LMS.System.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course save(Course course) {
        log.info("Creating new course");
        log.debug("Course data: {}", course);

        try {
            Course savedCourse = courseRepository.save(course);
            log.info("Course created successfully with ID: {}", savedCourse.getId());
            log.debug("Saved course details: {}", savedCourse);
            return savedCourse;
        } catch (Exception e) {
            log.error("Error creating course: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<Course> findAll() {
        log.info("Fetching all courses");

        try {
            List<Course> courses = courseRepository.findAll();
            log.info("Found {} courses", courses.size());
            log.debug("Courses details: {}", courses);
            return courses;
        } catch (Exception e) {
            log.error("Error fetching all courses: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Optional<Course> findByIdOptional(Long id) {
        log.info("Searching for course with ID: {}", id);

        try {
            Optional<Course> course = courseRepository.findById(id);
            if (course.isPresent()) {
                log.info("Course found with ID: {}", id);
                log.debug("Course details: {}", course.get());
            } else {
                log.info("Course not found with ID: {}", id);
            }
            return course;
        } catch (Exception e) {
            log.error("Error searching for course with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public Course findById(Long id) {
        log.info("Finding course by ID: {}", id);

        try {
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("Course not found with ID: {}", id);
                        return new CourseNotFoundException(id);
                    });

            log.info("Course found successfully with ID: {}", id);
            log.debug("Course details: {}", course);
            return course;
        } catch (CourseNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error finding course with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public boolean existsById(Long id) {
        log.info("Checking if course exists with ID: {}", id);

        try {
            boolean exists = courseRepository.existsById(id);
            log.info("Course with ID {} exists: {}", id, exists);
            return exists;
        } catch (Exception e) {
            log.error("Error checking course existence with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public List<Course> findByNameContaining(String name) {
        log.info("Searching courses containing name: {}", name);

        try {
            List<Course> courses = courseRepository.findByNameContainingIgnoreCase(name);
            log.info("Found {} courses containing name: {}", courses.size(), name);
            log.debug("Search results: {}", courses);
            return courses;
        } catch (Exception e) {
            log.error("Error searching courses by name '{}': {}", name, e.getMessage(), e);
            throw e;
        }
    }

    public void deleteById(Long id) {
        log.info("Deleting course with ID: {}", id);

        try {
            if (!courseRepository.existsById(id)) {
                log.error("Attempted to delete non-existent course with ID: {}", id);
                throw new CourseNotFoundException(id);
            }

            courseRepository.deleteById(id);
            log.info("Course deleted successfully with ID: {}", id);
        } catch (CourseNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error deleting course with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public void delete(Course course) {
        log.info("Deleting course: {}", course.getName());
        log.debug("Course to delete: {}", course);

        try {
            courseRepository.delete(course);
            log.info("Course deleted successfully: {}", course.getName());
        } catch (Exception e) {
            log.error("Error deleting course '{}': {}", course.getName(), e.getMessage(), e);
            throw e;
        }
    }

    public long count() {
        log.info("Counting all courses");

        try {
            long count = courseRepository.count();
            log.info("Total courses count: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error counting courses: {}", e.getMessage(), e);
            throw e;
        }
    }
}