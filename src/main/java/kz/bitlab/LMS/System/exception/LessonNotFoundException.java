package kz.bitlab.LMS.System.exception;

public class LessonNotFoundException extends IllegalArgumentException {
    public LessonNotFoundException(String message) {
        super(message);
    }

    public LessonNotFoundException(Long id) {
        super("Lesson not found with id: " + id);
    }
}