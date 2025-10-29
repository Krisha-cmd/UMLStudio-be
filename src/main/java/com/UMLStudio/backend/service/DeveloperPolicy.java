package com.UMLStudio.backend.service;

import com.UMLStudio.backend.service.interfaces.ProjectAccessPolicy;

public class DeveloperPolicy implements ProjectAccessPolicy {

    @Override
    public Boolean canSave() {
        return true;
    }

}
