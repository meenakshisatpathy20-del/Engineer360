package com.engineer360.note;

import com.engineer360.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByProject(Project project);

    Optional<Note> findByIdAndProject(Long id, Project project);
}