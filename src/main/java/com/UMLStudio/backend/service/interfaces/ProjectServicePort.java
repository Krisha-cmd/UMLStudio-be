package com.UMLStudio.backend.service;

import com.UMLStudio.backend.dto.ProjectRequest;
import com.UMLStudio.backend.dto.ProjectResponse;

import java.util.List;

public interface ProjectServicePort {
    ProjectResponse createProject(ProjectRequest request);
    List<ProjectResponse> listProjects();
    ProjectResponse getProject(Long id);
}

