package kz.bitlab.LMS.System.mapper;

import kz.bitlab.LMS.System.dto.request.CreateChapterRequest;
import kz.bitlab.LMS.System.dto.request.UpdateChapterRequest;
import kz.bitlab.LMS.System.dto.response.ChapterResponse;
import kz.bitlab.LMS.System.model.Chapter;
import kz.bitlab.LMS.System.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChapterMapper {

    public Chapter toEntity(CreateChapterRequest request, Course course) {
        if (request == null) {
            return null;
        }

        Chapter chapter = new Chapter();
        chapter.setName(request.getName());
        chapter.setDescription(request.getDescription());
        chapter.setOrder(request.getOrder());
        chapter.setCourse(course);
        return chapter;
    }

    public ChapterResponse toResponse(Chapter chapter) {
        if (chapter == null) {
            return null;
        }

        ChapterResponse response = new ChapterResponse();
        response.setId(chapter.getId());
        response.setName(chapter.getName());
        response.setDescription(chapter.getDescription());
        response.setOrder(chapter.getOrder());
        response.setCreatedTime(chapter.getCreatedTime());
        response.setUpdatedTime(chapter.getUpdatedTime());

        if (chapter.getCourse() != null) {
            response.setCourseId(chapter.getCourse().getId());
            response.setCourseName(chapter.getCourse().getName());
        }

        return response;
    }

    public void updateEntity(Chapter chapter, UpdateChapterRequest request) {
        if (chapter == null || request == null) {
            return;
        }

        chapter.setName(request.getName());
        chapter.setDescription(request.getDescription());
        chapter.setOrder(request.getOrder());
    }

    public List<ChapterResponse> toResponseList(List<Chapter> chapters) {
        if (chapters == null) {
            return null;
        }

        return chapters.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<Chapter> toEntityList(List<CreateChapterRequest> requests, Course course) {
        if (requests == null) {
            return null;
        }

        return requests.stream()
                .map(request -> toEntity(request, course))
                .collect(Collectors.toList());
    }
}