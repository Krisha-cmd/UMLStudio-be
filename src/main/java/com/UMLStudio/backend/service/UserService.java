package com.UMLStudio.backend.service;

import java.util.HashMap;
import java.util.List;

import com.UMLStudio.backend.model.Project;
import com.UMLStudio.backend.service.interfaces.AbstractUserService;

public class UserService implements AbstractUserService {

    @Override
    public List<Project> getProjectList(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProjectList'");
    }

    @Override
    public Project viewProject(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewProject'");
    }

    @Override
    public Project saveProject(Project project) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveProject'");
    }

    @Override
    public Boolean assignProject(Long projectId, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignProject'");
    }
    
}
