package com.engineer360.note;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public Note createNote(
            @PathVariable Long projectId,
            @RequestBody NoteRequest request,
            Authentication authentication
    ) {

        return noteService.createNote(
                projectId,
                request.getTitle(),
                request.getContent(),
                authentication.getName()
        );
    }

    @GetMapping
    public List<Note> getNotes(
            @PathVariable Long projectId,
            Authentication authentication
    ) {

        return noteService.getNotes(
                projectId,
                authentication.getName()
        );
    }

    @GetMapping("/{noteId}")
    public Note getNote(
            @PathVariable Long projectId,
            @PathVariable Long noteId,
            Authentication authentication
    ) {

        return noteService.getNote(
                projectId,
                noteId,
                authentication.getName()
        );
    }

    @PutMapping("/{noteId}")
    public Note updateNote(
            @PathVariable Long projectId,
            @PathVariable Long noteId,
            @RequestBody NoteRequest request,
            Authentication authentication
    ) {

        return noteService.updateNote(
                projectId,
                noteId,
                request.getTitle(),
                request.getContent(),
                authentication.getName()
        );
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(
            @PathVariable Long projectId,
            @PathVariable Long noteId,
            Authentication authentication
    ) {

        noteService.deleteNote(
                projectId,
                noteId,
                authentication.getName()
        );
    }
}