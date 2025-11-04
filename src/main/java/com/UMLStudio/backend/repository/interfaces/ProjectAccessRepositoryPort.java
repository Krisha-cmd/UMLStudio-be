package com.UMLStudio.backend.repository.interfaces;

import java.util.List;

import com.UMLStudio.backend.model.ProjectAccess;

public interface ProjectAccessRepositoryPort {

    List<ProjectAccess> findAllByUserId(Long userId);

}
