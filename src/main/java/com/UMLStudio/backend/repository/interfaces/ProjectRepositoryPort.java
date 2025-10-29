package com.UMLStudio.backend.repository;

import com.UMLStudio.backend.dto.ProjectResponse;
import com.UMLStudio.backend.model.Project;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryPort {
    Project save(Project project);
    List<Project> findAll();
    Optional<Project> findById(Long id);
}

