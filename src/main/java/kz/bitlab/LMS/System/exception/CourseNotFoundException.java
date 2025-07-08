package kz.bitlab.LMS.System.exception;

public class CourseNotFoundException extends IllegalArgumentException {
    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException(Long id) {
        super("Course not found with id: " + id);
    }
}