package com.engineer360.note;

import com.engineer360.project.Project;
import com.engineer360.project.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final ProjectService projectService;

    public NoteService(
            NoteRepository noteRepository,
            ProjectService projectService
    ) {
        this.noteRepository = noteRepository;
        this.projectService = projectService;
    }

    public Note createNote(
            Long projectId,
            String title,
            String content,
            String email
    ) {

        Project project = projectService.getProject(projectId, email);

        Note note = new Note(title, content, project);

        return noteRepository.save(note);
    }

    public List<Note> getNotes(
            Long projectId,
            String email
    ) {

        Project project = projectService.getProject(projectId, email);

        return noteRepository.findByProject(project);
    }

    public Note getNote(
            Long projectId,
            Long noteId,
            String email
    ) {

        Project project = projectService.getProject(projectId, email);

        return noteRepository.findByIdAndProject(noteId, project)
                .orElseThrow(() ->
                        new IllegalArgumentException("Note not found")
                );
    }

    public Note updateNote(
            Long projectId,
            Long noteId,
            String title,
            String content,
            String email
    ) {

        Note note = getNote(projectId, noteId, email);

        note.setTitle(title);
        note.setContent(content);

        return noteRepository.save(note);
    }

    public void deleteNote(
            Long projectId,
            Long noteId,
            String email
    ) {

        Note note = getNote(projectId, noteId, email);

        noteRepository.delete(note);
    }
}