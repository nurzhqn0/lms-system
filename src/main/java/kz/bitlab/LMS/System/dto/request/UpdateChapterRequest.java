package kz.bitlab.LMS.System.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateChapterRequest {
    @NotBlank(message = "Chapter name is required")
    @Size(max = 255, message = "Chapter name must not exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Order number is required")
    private Integer order;
}