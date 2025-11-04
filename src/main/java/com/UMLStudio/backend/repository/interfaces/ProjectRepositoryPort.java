package com.UMLStudio.backend.repository.interfaces;

import com.UMLStudio.backend.model.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryPort {
    Project save(Project project);
    List<Project> findAll();
    Optional<Project> findById(Long id);
    List<Project> findAllByUserId(Long userId);
}

