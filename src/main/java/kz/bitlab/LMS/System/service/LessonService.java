package kz.bitlab.LMS.System.service;

import kz.bitlab.LMS.System.exception.LessonNotFoundException;
import kz.bitlab.LMS.System.model.Lesson;
import kz.bitlab.LMS.System.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public Lesson save(Lesson lesson) {
        log.info("Creating new lesson");
        log.debug("Lesson data: {}", lesson);

        try {
            Lesson savedLesson = lessonRepository.save(lesson);
            log.info("Lesson created successfully with ID: {}", savedLesson.getId());
            log.debug("Saved lesson details: {}", savedLesson);
            return savedLesson;
        } catch (Exception e) {
            log.error("Error creating lesson: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<Lesson> findAll() {
        log.info("Fetching all lessons");

        try {
            List<Lesson> lessons = lessonRepository.findAll();
            log.info("Found {} lessons", lessons.size());
            log.debug("Lessons details: {}", lessons);
            return lessons;
        } catch (Exception e) {
            log.error("Error fetching all lessons: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Optional<Lesson> findByIdOptional(Long id) {
        log.info("Searching for lesson with ID: {}", id);

        try {
            Optional<Lesson> lesson = lessonRepository.findById(id);
            if (lesson.isPresent()) {
                log.info("Lesson found with ID: {}", id);
                log.debug("Lesson details: {}", lesson.get());
            } else {
                log.info("Lesson not found with ID: {}", id);
            }
            return lesson;
        } catch (Exception e) {
            log.error("Error searching for lesson with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public Lesson findById(Long id) {
        log.info("Finding lesson by ID: {}", id);

        try {
            Lesson lesson = lessonRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("Lesson not found with ID: {}", id);
                        return new LessonNotFoundException(id);
                    });

            log.info("Lesson found successfully with ID: {}", id);
            log.debug("Lesson details: {}", lesson);
            return lesson;
        } catch (LessonNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error finding lesson with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public boolean existsById(Long id) {
        log.info("Checking if lesson exists with ID: {}", id);

        try {
            boolean exists = lessonRepository.existsById(id);
            log.info("Lesson with ID {} exists: {}", id, exists);
            return exists;
        } catch (Exception e) {
            log.error("Error checking lesson existence with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public List<Lesson> findByChapterId(Long chapterId) {
        log.info("Finding lessons for chapter ID: {}", chapterId);

        try {
            List<Lesson> lessons = lessonRepository.findByChapterIdOrderByOrder(chapterId);
            log.info("Found {} lessons for chapter ID: {}", lessons.size(), chapterId);
            log.debug("Lessons for chapter {}: {}", chapterId, lessons);
            return lessons;
        } catch (Exception e) {
            log.error("Error finding lessons for chapter ID {}: {}", chapterId, e.getMessage(), e);
            throw e;
        }
    }

    public List<Lesson> findByChapterIdOrderByOrder(Long chapterId) {
        log.info("Finding lessons for chapter ID ordered by order: {}", chapterId);

        try {
            List<Lesson> lessons = lessonRepository.findByChapterIdOrderByOrder(chapterId);
            log.info("Found {} ordered lessons for chapter ID: {}", lessons.size(), chapterId);
            log.debug("Ordered lessons for chapter {}: {}", chapterId, lessons);
            return lessons;
        } catch (Exception e) {
            log.error("Error finding ordered lessons for chapter ID {}: {}", chapterId, e.getMessage(), e);
            throw e;
        }
    }

    public List<Lesson> findByNameContaining(String name) {
        log.info("Searching lessons containing name: {}", name);

        try {
            List<Lesson> lessons = lessonRepository.findByNameContainingIgnoreCase(name);
            log.info("Found {} lessons containing name: {}", lessons.size(), name);
            log.debug("Search results: {}", lessons);
            return lessons;
        } catch (Exception e) {
            log.error("Error searching lessons by name '{}': {}", name, e.getMessage(), e);
            throw e;
        }
    }

    public void deleteById(Long id) {
        log.info("Deleting lesson with ID: {}", id);

        try {
            if (!lessonRepository.existsById(id)) {
                log.error("Attempted to delete non-existent lesson with ID: {}", id);
                throw new LessonNotFoundException(id);
            }

            lessonRepository.deleteById(id);
            log.info("Lesson deleted successfully with ID: {}", id);
        } catch (LessonNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error deleting lesson with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public void delete(Lesson lesson) {
        log.info("Deleting lesson: {}", lesson.getName());
        log.debug("Lesson to delete: {}", lesson);

        try {
            lessonRepository.delete(lesson);
            log.info("Lesson deleted successfully: {}", lesson.getName());
        } catch (Exception e) {
            log.error("Error deleting lesson '{}': {}", lesson.getName(), e.getMessage(), e);
            throw e;
        }
    }

    public long count() {
        log.info("Counting all lessons");

        try {
            long count = lessonRepository.count();
            log.info("Total lessons count: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error counting lessons: {}", e.getMessage(), e);
            throw e;
        }
    }

    public long countByChapterId(Long chapterId) {
        log.info("Counting lessons for chapter ID: {}", chapterId);

        try {
            long count = lessonRepository.countByChapterId(chapterId);
            log.info("Lessons count for chapter ID {}: {}", chapterId, count);
            return count;
        } catch (Exception e) {
            log.error("Error counting lessons for chapter ID {}: {}", chapterId, e.getMessage(), e);
            throw e;
        }
    }
}