package kz.bitlab.LMS.System.dto.converter;

import kz.bitlab.LMS.System.dto.request.CreateChapterRequest;
import kz.bitlab.LMS.System.dto.request.UpdateChapterRequest;
import kz.bitlab.LMS.System.dto.response.ChapterResponse;
import kz.bitlab.LMS.System.model.Chapter;
import kz.bitlab.LMS.System.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChapterConverter {

    public Chapter toEntity(CreateChapterRequest request) {
        Chapter chapter = new Chapter();
        chapter.setName(request.getName());
        chapter.setDescription(request.getDescription());
        chapter.setOrder(request.getOrder());

        // Set course reference - you'll need to fetch the course in the service
        Course course = new Course();
        course.setId(request.getCourseId());
        chapter.setCourse(course);

        return chapter;
    }

    public void updateEntity(Chapter chapter, UpdateChapterRequest request) {
        chapter.setName(request.getName());
        chapter.setDescription(request.getDescription());
        chapter.setOrder(request.getOrder());
    }

    public ChapterResponse toResponse(Chapter chapter) {
        ChapterResponse response = new ChapterResponse();
        response.setId(chapter.getId());
        response.setName(chapter.getName());
        response.setDescription(chapter.getDescription());
        response.setOrder(chapter.getOrder());
        response.setCreatedTime(chapter.getCreatedTime());
        response.setUpdatedTime(chapter.getUpdatedTime());

        // Set course information if available
        if (chapter.getCourse() != null) {
            response.setCourseId(chapter.getCourse().getId());
            response.setCourseName(chapter.getCourse().getName());
        }

        return response;
    }

    public List<ChapterResponse> toResponseList(List<Chapter> chapters) {
        return chapters.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}