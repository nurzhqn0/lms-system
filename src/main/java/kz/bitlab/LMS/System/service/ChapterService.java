package kz.bitlab.LMS.System.service;

import kz.bitlab.LMS.System.exception.ChapterNotFoundException;
import kz.bitlab.LMS.System.model.Chapter;
import kz.bitlab.LMS.System.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;

    public Chapter save(Chapter chapter) {
        log.info("Creating new chapter");
        log.debug("Chapter data: {}", chapter);

        try {
            Chapter savedChapter = chapterRepository.save(chapter);
            log.info("Chapter created successfully with ID: {}", savedChapter.getId());
            log.debug("Saved chapter details: {}", savedChapter);
            return savedChapter;
        } catch (Exception e) {
            log.error("Error creating chapter: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<Chapter> findAll() {
        log.info("Fetching all chapters");

        try {
            List<Chapter> chapters = chapterRepository.findAll();
            log.info("Found {} chapters", chapters.size());
            log.debug("Chapters details: {}", chapters);
            return chapters;
        } catch (Exception e) {
            log.error("Error fetching all chapters: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Optional<Chapter> findByIdOptional(Long id) {
        log.info("Searching for chapter with ID: {}", id);

        try {
            Optional<Chapter> chapter = chapterRepository.findById(id);
            if (chapter.isPresent()) {
                log.info("Chapter found with ID: {}", id);
                log.debug("Chapter details: {}", chapter.get());
            } else {
                log.info("Chapter not found with ID: {}", id);
            }
            return chapter;
        } catch (Exception e) {
            log.error("Error searching for chapter with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public Chapter findById(Long id) {
        log.info("Finding chapter by ID: {}", id);

        try {
            Chapter chapter = chapterRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("Chapter not found with ID: {}", id);
                        return new ChapterNotFoundException(id);
                    });

            log.info("Chapter found successfully with ID: {}", id);
            log.debug("Chapter details: {}", chapter);
            return chapter;
        } catch (ChapterNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error finding chapter with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public boolean existsById(Long id) {
        log.info("Checking if chapter exists with ID: {}", id);

        try {
            boolean exists = chapterRepository.existsById(id);
            log.info("Chapter with ID {} exists: {}", id, exists);
            return exists;
        } catch (Exception e) {
            log.error("Error checking chapter existence with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public List<Chapter> findByCourseId(Long courseId) {
        log.info("Finding chapters for course ID: {}", courseId);

        try {
            List<Chapter> chapters = chapterRepository.findByCourseIdOrderByOrder(courseId);
            log.info("Found {} chapters for course ID: {}", chapters.size(), courseId);
            log.debug("Chapters for course {}: {}", courseId, chapters);
            return chapters;
        } catch (Exception e) {
            log.error("Error finding chapters for course ID {}: {}", courseId, e.getMessage(), e);
            throw e;
        }
    }

    public List<Chapter> findByCourseIdOrderByOrder(Long courseId) {
        log.info("Finding chapters for course ID ordered by order: {}", courseId);

        try {
            List<Chapter> chapters = chapterRepository.findByCourseIdOrderByOrder(courseId);
            log.info("Found {} ordered chapters for course ID: {}", chapters.size(), courseId);
            log.debug("Ordered chapters for course {}: {}", courseId, chapters);
            return chapters;
        } catch (Exception e) {
            log.error("Error finding ordered chapters for course ID {}: {}", courseId, e.getMessage(), e);
            throw e;
        }
    }

    public List<Chapter> findByNameContaining(String name) {
        log.info("Searching chapters containing name: {}", name);

        try {
            List<Chapter> chapters = chapterRepository.findByNameContainingIgnoreCase(name);
            log.info("Found {} chapters containing name: {}", chapters.size(), name);
            log.debug("Search results: {}", chapters);
            return chapters;
        } catch (Exception e) {
            log.error("Error searching chapters by name '{}': {}", name, e.getMessage(), e);
            throw e;
        }
    }

    public boolean existsByCourseIdAndOrder(Long courseId, int order) {
        log.info("Checking if chapter exists with course ID: {} and order: {}", courseId, order);

        try {
            boolean exists = chapterRepository.existsByCourseIdAndOrder(courseId, order);
            log.info("Chapter with course ID {} and order {} exists: {}", courseId, order, exists);
            return exists;
        } catch (Exception e) {
            log.error("Error checking chapter existence with course ID {} and order {}: {}",
                    courseId, order, e.getMessage(), e);
            throw e;
        }
    }

    public void deleteById(Long id) {
        log.info("Deleting chapter with ID: {}", id);

        try {
            if (!chapterRepository.existsById(id)) {
                log.error("Attempted to delete non-existent chapter with ID: {}", id);
                throw new ChapterNotFoundException(id);
            }

            chapterRepository.deleteById(id);
            log.info("Chapter deleted successfully with ID: {}", id);
        } catch (ChapterNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error deleting chapter with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public void delete(Chapter chapter) {
        log.info("Deleting chapter: {}", chapter.getName());
        log.debug("Chapter to delete: {}", chapter);

        try {
            chapterRepository.delete(chapter);
            log.info("Chapter deleted successfully: {}", chapter.getName());
        } catch (Exception e) {
            log.error("Error deleting chapter '{}': {}", chapter.getName(), e.getMessage(), e);
            throw e;
        }
    }

    public long count() {
        log.info("Counting all chapters");

        try {
            long count = chapterRepository.count();
            log.info("Total chapters count: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error counting chapters: {}", e.getMessage(), e);
            throw e;
        }
    }

    public long countByCourseId(Long courseId) {
        log.info("Counting chapters for course ID: {}", courseId);

        try {
            long count = chapterRepository.countByCourseId(courseId);
            log.info("Chapters count for course ID {}: {}", courseId, count);
            return count;
        } catch (Exception e) {
            log.error("Error counting chapters for course ID {}: {}", courseId, e.getMessage(), e);
            throw e;
        }
    }
}