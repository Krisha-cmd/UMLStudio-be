package com.UMLStudio.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.UMLStudio.backend.Utils.AccessPolicy;
import com.UMLStudio.backend.dto.ProjectDto;
import com.UMLStudio.backend.dto.ProjectRequest;
import com.UMLStudio.backend.model.Project;
import com.UMLStudio.backend.model.ProjectAccess;
import com.UMLStudio.backend.repository.ProjectAccessRepository;
import com.UMLStudio.backend.repository.ProjectRepository;
import com.UMLStudio.backend.repository.UserRepository;
import com.UMLStudio.backend.repository.interfaces.ProjectAccessRepositoryPort;
import com.UMLStudio.backend.repository.interfaces.ProjectRepositoryPort;
import com.UMLStudio.backend.service.interfaces.ProjectAccessManagerPort;
import com.UMLStudio.backend.service.interfaces.ProjectServicePort;
import com.UMLStudio.backend.service.interfaces.UserServicePort;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserRepository userrepo;
    private final ProjectRepository projectrepo;
    private final ProjectAccessManagerPort projectAccessManager;
    private final ModelMapper mapper;
    private final ProjectServicePort projectService;
    private final ProjectRepositoryPort projectRepository;
    private final ProjectAccessRepository projectAccessRepository;


    @Override
    public List<ProjectDto> getProjectList(Long userId) {
        List<ProjectDto> dto=projectAccessManager.getAssignedProjects(userId)
                            .stream()
                            .map((projectAccess)->mapper.map(projectAccess, ProjectDto.class))
                            .toList();
        
        dto=projectService
        .listProjects()
        .stream()
        .map((project)->mapper.map(project,ProjectDto.class))
        .toList();

        return dto;

    }

    @Override
    public Optional<Project> viewProject(Long projectId) {
        return projectRepository.findById(projectId);
    }


    @Override
    public Project saveProject(ProjectRequest projectrq) {
       return projectRepository.save(mapper.map(projectrq, Project.class));
    }

    @Override
    public Boolean assignProject(Long projectId, Long userId,Long assigneeId) {
        if(projectAccessManager.hasAccess(userId, projectId)){
            ProjectAccess project=new ProjectAccess(userId,projectId,AccessPolicy.Viewer);
            projectAccessRepository.save(project);
            return true;
        }
        return false;
    }    
}
