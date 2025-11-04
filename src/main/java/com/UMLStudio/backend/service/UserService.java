package com.UMLStudio.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.UMLStudio.backend.Utils.AccessPolicy;
import com.UMLStudio.backend.dto.ProjectDto;
import com.UMLStudio.backend.dto.ProjectRequest;
import com.UMLStudio.backend.model.Project;
import com.UMLStudio.backend.model.ProjectAccess;
import com.UMLStudio.backend.repository.ProjectAccessRepository;
import com.UMLStudio.backend.repository.interfaces.ProjectRepositoryPort;
import com.UMLStudio.backend.service.interfaces.ProjectAccessManagerPort;
import com.UMLStudio.backend.service.interfaces.ProjectServicePort;
import com.UMLStudio.backend.service.interfaces.UserServicePort;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final ProjectAccessManagerPort projectAccessManager;
    private final ModelMapper mapper;
    private final ProjectServicePort projectService;
    private final ProjectRepositoryPort projectRepository;
    private final ProjectAccessRepository projectAccessRepository;


    @Override
    public List<ProjectDto> getProjectList(Long userId) {
        List<ProjectDto> projects=projectService.listProjects(userId);
        List<ProjectAccess> projectAccesses=projectAccessManager.getAssignedProjects(userId);
        Map<Long,AccessPolicy> map=new HashMap<>();
        projectAccesses.stream().forEach((entity)->map.put(entity.getProjectId(),entity.getAccessPolicy()));
        projects.stream().forEach((entity)->entity.setAccessPolicy(map.get(entity.getProjectId())));
        return projects;
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
            ProjectAccess project=new ProjectAccess(assigneeId,projectId,AccessPolicy.Viewer);
            projectAccessRepository.save(project);
            return true;
        }
        return false;
    }    
}
