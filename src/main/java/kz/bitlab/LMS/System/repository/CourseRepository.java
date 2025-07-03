package kz.bitlab.LMS.System.repository;

import kz.bitlab.LMS.System.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

    List<Course> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.chapters")
    List<Course> findAllWithChapters();

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.chapters ch LEFT JOIN FETCH ch.lessons WHERE c.id = :id")
    Optional<Course> findByIdWithChaptersAndLessons(Long id);
}
