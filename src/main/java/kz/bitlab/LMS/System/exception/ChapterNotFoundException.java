package kz.bitlab.LMS.System.exception;

public class ChapterNotFoundException extends IllegalArgumentException {
    public ChapterNotFoundException(String message) {
        super(message);
    }

    public ChapterNotFoundException(Long id) {
        super("Chapter not found with id: " + id);
    }
}