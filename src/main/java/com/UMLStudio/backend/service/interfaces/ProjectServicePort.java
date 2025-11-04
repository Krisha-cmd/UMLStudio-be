package com.UMLStudio.backend.service.interfaces;

import com.UMLStudio.backend.dto.ProjectDto;
import com.UMLStudio.backend.dto.ProjectRequest;
import com.UMLStudio.backend.dto.ProjectResponse;

import java.util.List;

public interface ProjectServicePort {
    ProjectResponse createProject(ProjectRequest request);
    ProjectResponse getProject(Long id);
    List<ProjectDto> listProjects(Long userId);
}

