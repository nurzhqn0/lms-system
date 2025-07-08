package kz.bitlab.LMS.System.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CourseResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
