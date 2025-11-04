package com.UMLStudio.backend.controller;

import com.UMLStudio.backend.dto.ProjectDto;
import com.UMLStudio.backend.dto.ProjectRequest;
import com.UMLStudio.backend.dto.ProjectResponse;
import com.UMLStudio.backend.model.User;
import com.UMLStudio.backend.service.interfaces.ProjectServicePort;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServicePort projectService;

    // @PostMapping
    // public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request) {
    //     ProjectResponse created = projectService.createProject(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(created);
    // }

    // @GetMapping
    // public ResponseEntity<List<ProjectResponse>> listProjects() {
    //     List<ProjectResponse> list = projectService.listProjects();
    //     return ResponseEntity.ok(list);
    // }
 
    // @GetMapping("/{id}")
    // public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
    //     ProjectResponse response = projectService.getProject(id);
    //     return ResponseEntity.ok(response);
    // }

    @GetMapping("/getProjectList")
    public ResponseEntity<?> getProjectList(HttpServletRequest request){
        try{
            User user=(User) request.getAttribute("authenticatedUser");
            if(user == null){
                return ResponseEntity.badRequest().body(Map.of(
                    "status","FAILED",
                    "message","invalid or missing token"
                ));
            }
            Long userId=user.getUserId();
            List<ProjectDto> projects=projectService.listProjects(userId);
            if(projects.isEmpty()){
                return ResponseEntity.status(404).body(Map.of(
                    "status","FAILED", 
                    "message" , "No projects found for the given user"
                ));
            }
            return ResponseEntity.ok().body(Map.of(
                "status","SUCCESS",
                "message","Project list fetched successfully",
                "result",projects
            ));
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(Map.of(
                "status","FAILED",
                "message","Something went wrong while fetching project list"
            ));
        }
    } 
    



}
