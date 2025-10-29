package com.UMLStudio.backend.service.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.UMLStudio.backend.model.Project;

public interface AbstractUserService {

    public List<Project> getProjectList(Long id);
    public Project viewProject(Long id);
    public Project saveProject(Project project);
    public Boolean assignProject(Long projectId,Long userId);

}
