package kz.bitlab.LMS.System.repository;

import kz.bitlab.LMS.System.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByChapterIdOrderByOrder(Long chapterId);

    List<Lesson> findByChapterId(Long chapterId);

    @Query("SELECT l FROM Lesson l WHERE l.chapter.id = :chapterId ORDER BY l.order ASC")
    List<Lesson> findLessonsByChapterIdSorted(@Param("chapterId") Long chapterId);

    @Query("SELECT l FROM Lesson l WHERE l.chapter.course.id = :courseId")
    List<Lesson> findLessonsByCourseId(@Param("courseId") Long courseId);

    List<Lesson> findByNameContainingIgnoreCase(String name);

    long countByChapterId(Long chapterId);
}
