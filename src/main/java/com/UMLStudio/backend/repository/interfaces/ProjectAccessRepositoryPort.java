package com.UMLStudio.backend.repository.interfaces;

import java.util.List;

import com.UMLStudio.backend.Utils.AccessPolicy;
import com.UMLStudio.backend.model.ProjectAccess;

public interface ProjectAccessRepositoryPort {

    List<ProjectAccess> findAllByUserId(Long userId);

    AccessPolicy findAccessPolicyByUserIdAndProjectId(Long userId, Long projectId);

    ProjectAccess save(ProjectAccess projectAccess);

}
