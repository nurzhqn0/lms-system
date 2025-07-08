package kz.bitlab.LMS.System.mapper;

import kz.bitlab.LMS.System.dto.request.CreateLessonRequest;
import kz.bitlab.LMS.System.dto.request.UpdateLessonRequest;
import kz.bitlab.LMS.System.dto.response.LessonResponse;
import kz.bitlab.LMS.System.model.Lesson;
import kz.bitlab.LMS.System.model.Chapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessonMapper {

    public Lesson toEntity(CreateLessonRequest request, Chapter chapter) {
        if (request == null) {
            return null;
        }

        Lesson lesson = new Lesson();
        lesson.setName(request.getName());
        lesson.setDescription(request.getDescription());
        lesson.setContent(request.getContent());
        lesson.setOrder(request.getOrder());
        lesson.setChapter(chapter);
        return lesson;
    }

    public LessonResponse toResponse(Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        LessonResponse response = new LessonResponse();
        response.setId(lesson.getId());
        response.setName(lesson.getName());
        response.setDescription(lesson.getDescription());
        response.setContent(lesson.getContent());
        response.setOrder(lesson.getOrder());
        response.setCreatedTime(lesson.getCreatedTime());
        response.setUpdatedTime(lesson.getUpdatedTime());

        if (lesson.getChapter() != null) {
            response.setChapterId(lesson.getChapter().getId());
            response.setChapterName(lesson.getChapter().getName());
        }

        return response;
    }

    public void updateEntity(Lesson lesson, UpdateLessonRequest request) {
        if (lesson == null || request == null) {
            return;
        }

        lesson.setName(request.getName());
        lesson.setDescription(request.getDescription());
        lesson.setContent(request.getContent());
        lesson.setOrder(request.getOrder());
    }

    public List<LessonResponse> toResponseList(List<Lesson> lessons) {
        if (lessons == null) {
            return null;
        }

        return lessons.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<Lesson> toEntityList(List<CreateLessonRequest> requests, Chapter chapter) {
        if (requests == null) {
            return null;
        }

        return requests.stream()
                .map(request -> toEntity(request, chapter))
                .collect(Collectors.toList());
    }
}