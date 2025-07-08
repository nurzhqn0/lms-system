package kz.bitlab.LMS.System.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChapterResponse {
    private Long id;
    private String name;
    private String description;
    private int order;
    private Long courseId;
    private String courseName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
