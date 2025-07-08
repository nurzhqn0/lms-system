package kz.bitlab.LMS.System.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LessonResponse {
    private Long id;
    private String name;
    private String description;
    private String content;
    private int order;
    private Long chapterId;
    private String chapterName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}