package kz.bitlab.LMS.System.service;

import kz.bitlab.LMS.System.exception.ChapterNotFoundException;
import kz.bitlab.LMS.System.model.Chapter;
import kz.bitlab.LMS.System.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;

    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public List<Chapter> findAll() {
        return chapterRepository.findAll();
    }

    public Optional<Chapter> findByIdOptional(Long id) {
        return chapterRepository.findById(id);
    }

    public Chapter findById(Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new ChapterNotFoundException(id));
    }

    public boolean existsById(Long id) {
        return chapterRepository.existsById(id);
    }

    public List<Chapter> findByCourseId(Long courseId) {
        return chapterRepository.findByCourseIdOrderByOrder(courseId);
    }

    public List<Chapter> findByCourseIdOrderByOrder(Long courseId) {
        return chapterRepository.findByCourseIdOrderByOrder(courseId);
    }

    public List<Chapter> findByNameContaining(String name) {
        return chapterRepository.findByNameContainingIgnoreCase(name);
    }

    public boolean existsByCourseIdAndOrder(Long courseId, int order) {
        return chapterRepository.existsByCourseIdAndOrder(courseId, order);
    }

    public void deleteById(Long id) {
        if (!chapterRepository.existsById(id)) {
            throw new ChapterNotFoundException(id);
        }
        chapterRepository.deleteById(id);
    }

    public void delete(Chapter chapter) {
        chapterRepository.delete(chapter);
    }

    public long count() {
        return chapterRepository.count();
    }

    public long countByCourseId(Long courseId) {
        return chapterRepository.countByCourseId(courseId);
    }
}