package com.UMLStudio.backend.service;
import org.springframework.stereotype.Service;
import com.UMLStudio.backend.Utils.AccessPolicy;
import com.UMLStudio.backend.service.interfaces.ProjectAccessPolicy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectAccessPolicy {

    private final DeveloperPolicy developerPolicy;
    private final ViewerPolicy viewerPolicy;

    public ProjectAccessPolicy getPolicy(AccessPolicy accessPolicy) {
        return switch (accessPolicy) {
            case AccessPolicy.Developer -> developerPolicy;
            case AccessPolicy.Viewer -> viewerPolicy;
        };
    }
}
