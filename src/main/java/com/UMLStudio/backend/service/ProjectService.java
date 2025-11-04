package com.UMLStudio.backend.service;

import com.UMLStudio.backend.Utils.AccessPolicy;
import com.UMLStudio.backend.dto.ProjectDto;
import com.UMLStudio.backend.dto.ProjectRequest;
import com.UMLStudio.backend.dto.ProjectResponse;
import com.UMLStudio.backend.exception.ResourceNotFoundException;
import com.UMLStudio.backend.model.Project;
import com.UMLStudio.backend.model.ProjectAccess;
import com.UMLStudio.backend.repository.ProjectAccessRepository;
import com.UMLStudio.backend.repository.interfaces.ProjectAccessRepositoryPort;
import com.UMLStudio.backend.repository.interfaces.ProjectRepositoryPort;
import com.UMLStudio.backend.service.interfaces.ProjectAccessManagerPort;
import com.UMLStudio.backend.service.interfaces.ProjectServicePort;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectServicePort {

    private final ProjectRepositoryPort projectRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Project project = new Project(request.getName(), request.getDescription());
        Project saved = projectRepository.save(project);
        return mapToResponse(saved);
    }

    @Override
    public List<ProjectDto> listProjects(Long userId) {
         return projectRepository.findAllByUserId(userId)
        .stream()
        .map((entity)->modelMapper.map(entity, ProjectDto.class))
        .toList();
    }

    @Override
    public ProjectResponse getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return mapToResponse(project);
    }

    private ProjectResponse mapToResponse(Project p) {
        return new ProjectResponse(p.getProjectId(), p.getName(), p.getProjectDescription(), p.getCreatedAt());
    }
}
