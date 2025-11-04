package com.UMLStudio.backend.service;

import com.UMLStudio.backend.service.interfaces.ProjectAccessPolicyPort;

public class ViewerPolicy implements ProjectAccessPolicyPort{

    @Override
    public Boolean canSave() {
        return false;
    }

}
