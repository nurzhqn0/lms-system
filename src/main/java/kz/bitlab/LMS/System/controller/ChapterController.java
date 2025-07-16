package kz.bitlab.LMS.System.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bitlab.LMS.System.dto.converter.ChapterConverter;
import kz.bitlab.LMS.System.dto.request.CreateChapterRequest;
import kz.bitlab.LMS.System.dto.request.UpdateChapterRequest;
import kz.bitlab.LMS.System.dto.response.ChapterResponse;
import kz.bitlab.LMS.System.dto.response.ErrorResponse;
import kz.bitlab.LMS.System.model.Chapter;
import kz.bitlab.LMS.System.service.ChapterService;
import kz.bitlab.LMS.System.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Chapter Management", description = "APIs for managing chapters in the LMS system")
public class ChapterController {

    private final ChapterService chapterService;
    private final CourseService courseService;
    private final ChapterConverter chapterConverter;

    @Operation(summary = "Create a new chapter", description = "Creates a new chapter for a specific course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Chapter created successfully",
                    content = @Content(schema = @Schema(implementation = ChapterResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ChapterResponse> createChapter(@Valid @RequestBody CreateChapterRequest request) {
        log.info("POST /api/chapters - Creating new chapter: {}", request.getName());

        // Validate that the course exists
        courseService.findById(request.getCourseId());

        Chapter chapter = chapterConverter.toEntity(request);
        Chapter savedChapter = chapterService.save(chapter);
        ChapterResponse response = chapterConverter.toResponse(savedChapter);

        log.info("Chapter created successfully with ID: {}", savedChapter.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all chapters", description = "Retrieves a list of all chapters in the system")
    @ApiResponse(responseCode = "200", description = "Chapters retrieved successfully")
    @GetMapping
    public ResponseEntity<List<ChapterResponse>> getAllChapters() {
        log.info("GET /api/chapters - Fetching all chapters");

        List<Chapter> chapters = chapterService.findAll();
        List<ChapterResponse> responses = chapterConverter.toResponseList(chapters);

        log.info("Retrieved {} chapters", chapters.size());

        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Get chapter by ID", description = "Retrieves a specific chapter by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chapter found",
                    content = @Content(schema = @Schema(implementation = ChapterResponse.class))),
            @ApiResponse(responseCode = "404", description = "Chapter not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChapterResponse> getChapterById(
            @Parameter(description = "Chapter ID", required = true) @PathVariable Long id) {
        log.info("GET /api/chapters/{} - Fetching chapter by ID", id);

        Chapter chapter = chapterService.findById(id);
        ChapterResponse response = chapterConverter.toResponse(chapter);

        log.info("Chapter found with ID: {}", id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/optional")
    public ResponseEntity<ChapterResponse> getChapterByIdOptional(@PathVariable Long id) {
        log.info("GET /api/chapters/{}/optional - Optionally fetching chapter by ID", id);

        Optional<Chapter> chapter = chapterService.findByIdOptional(id);

        if (chapter.isPresent()) {
            ChapterResponse response = chapterConverter.toResponse(chapter.get());
            log.info("Chapter found with ID: {}", id);
            return ResponseEntity.ok(response);
        } else {
            log.info("Chapter not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkChapterExists(@PathVariable Long id) {
        log.info("GET /api/chapters/{}/exists - Checking if chapter exists", id);

        boolean exists = chapterService.existsById(id);
        log.info("Chapter with ID {} exists: {}", id, exists);

        return ResponseEntity.ok(exists);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ChapterResponse>> getChaptersByCourseId(@PathVariable Long courseId) {
        log.info("GET /api/chapters/course/{} - Fetching chapters for course", courseId);

        List<Chapter> chapters = chapterService.findByCourseId(courseId);
        List<ChapterResponse> responses = chapterConverter.toResponseList(chapters);

        log.info("Found {} chapters for course ID: {}", chapters.size(), courseId);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/course/{courseId}/ordered")
    public ResponseEntity<List<ChapterResponse>> getChaptersByCourseIdOrderedByOrder(@PathVariable Long courseId) {
        log.info("GET /api/chapters/course/{}/ordered - Fetching ordered chapters for course", courseId);

        List<Chapter> chapters = chapterService.findByCourseIdOrderByOrder(courseId);
        List<ChapterResponse> responses = chapterConverter.toResponseList(chapters);

        log.info("Found {} ordered chapters for course ID: {}", chapters.size(), courseId);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ChapterResponse>> searchChaptersByName(@RequestParam String name) {
        log.info("GET /api/chapters/search?name={} - Searching chapters by name", name);

        List<Chapter> chapters = chapterService.findByNameContaining(name);
        List<ChapterResponse> responses = chapterConverter.toResponseList(chapters);

        log.info("Found {} chapters containing name: {}", chapters.size(), name);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/course/{courseId}/order/{order}/exists")
    public ResponseEntity<Boolean> checkChapterExistsByCourseIdAndOrder(
            @PathVariable Long courseId,
            @PathVariable int order) {
        log.info("GET /api/chapters/course/{}/order/{}/exists - Checking chapter existence", courseId, order);

        boolean exists = chapterService.existsByCourseIdAndOrder(courseId, order);
        log.info("Chapter with course ID {} and order {} exists: {}", courseId, order, exists);

        return ResponseEntity.ok(exists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChapterResponse> updateChapter(@PathVariable Long id, @Valid @RequestBody UpdateChapterRequest request) {
        log.info("PUT /api/chapters/{} - Updating chapter", id);

        Chapter existingChapter = chapterService.findById(id);
        chapterConverter.updateEntity(existingChapter, request);

        Chapter updatedChapter = chapterService.save(existingChapter);
        ChapterResponse response = chapterConverter.toResponse(updatedChapter);

        log.info("Chapter updated successfully with ID: {}", updatedChapter.getId());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapterById(@PathVariable Long id) {
        log.info("DELETE /api/chapters/{} - Deleting chapter by ID", id);

        chapterService.deleteById(id);
        log.info("Chapter deleted successfully with ID: {}", id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChapter(@Valid @RequestBody Chapter chapter) {
        log.info("DELETE /api/chapters - Deleting chapter: {}", chapter.getName());

        chapterService.delete(chapter);
        log.info("Chapter deleted successfully: {}", chapter.getName());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalChaptersCount() {
        log.info("GET /api/chapters/count - Getting total chapters count");

        long count = chapterService.count();
        log.info("Total chapters count: {}", count);

        return ResponseEntity.ok(count);
    }

    @GetMapping("/course/{courseId}/count")
    public ResponseEntity<Long> getChaptersCountByCourseId(@PathVariable Long courseId) {
        log.info("GET /api/chapters/course/{}/count - Getting chapters count for course", courseId);

        long count = chapterService.countByCourseId(courseId);
        log.info("Chapters count for course ID {}: {}", courseId, count);

        return ResponseEntity.ok(count);
    }
}