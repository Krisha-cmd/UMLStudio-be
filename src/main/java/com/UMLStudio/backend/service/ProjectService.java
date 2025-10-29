package com.UMLStudio.backend.service;

import com.UMLStudio.backend.dto.ProjectRequest;
import com.UMLStudio.backend.dto.ProjectResponse;
import com.UMLStudio.backend.exception.ResourceNotFoundException;
import com.UMLStudio.backend.model.Project;
import com.UMLStudio.backend.repository.interfaces.ProjectRepositoryPort;
import com.UMLStudio.backend.service.interfaces.ProjectServicePort;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService implements ProjectServicePort {

    private final ProjectRepositoryPort projectRepository;

    public ProjectService(ProjectRepositoryPort projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Project project = new Project(request.getName(), request.getDescription());
        Project saved = projectRepository.save(project);
        return mapToResponse(saved);
    }

    @Override
    public List<ProjectResponse> listProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return mapToResponse(project);
    }

    private ProjectResponse mapToResponse(Project p) {
        return new ProjectResponse(p.getProjectId(), p.getName(), p.getDescription(), p.getCreatedAt());
    }
}
