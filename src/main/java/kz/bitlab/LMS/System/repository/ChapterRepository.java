package kz.bitlab.LMS.System.repository;

import kz.bitlab.LMS.System.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    List<Chapter> findByCourseIdOrderByOrder(Long courseId);

    List<Chapter> findByCourseId(Long courseId);

    @Query("SELECT c FROM Chapter c WHERE c.course.id = :courseId ORDER BY c.order ASC")
    List<Chapter> findChaptersByCourseIdSorted(@Param("courseId") Long courseId);

    @Query("SELECT c FROM Chapter c LEFT JOIN FETCH c.lessons WHERE c.course.id = :courseId ORDER BY c.order")
    List<Chapter> findChaptersByCourseIdWithLessons(@Param("courseId") Long courseId);
}