package kz.bitlab.LMS.System.service;

import kz.bitlab.LMS.System.model.Lesson;
import kz.bitlab.LMS.System.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> findByIdOptional(Long id) {
        return lessonRepository.findById(id);
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
    }

    public boolean existsById(Long id) {
        return lessonRepository.existsById(id);
    }

    public List<Lesson> findByChapterId(Long chapterId) {
        return lessonRepository.findByChapterIdOrderByOrder(chapterId);
    }

    public List<Lesson> findByChapterIdOrderByOrder(Long chapterId) {
        return lessonRepository.findByChapterIdOrderByOrder(chapterId);
    }

    public List<Lesson> findByNameContaining(String name) {
        return lessonRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteById(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new RuntimeException("Lesson not found with id: " + id);
        }
        lessonRepository.deleteById(id);
    }

    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }


    public long count() {
        return lessonRepository.count();
    }

    public long countByChapterId(Long chapterId) {
        return lessonRepository.countByChapterId(chapterId);
    }
}