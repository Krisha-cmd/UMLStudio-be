package com.UMLStudio.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project{

    public Project(String name, String description) {
        this.projectName=name;
        this.projectDescription=description;
    }

    public Project(String projectName, String projectDescription, JsonNode projectDetails) {
        this.projectName=projectName;
        this.projectDescription=projectDescription;
        this.projectDetails=projectDetails;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(nullable = false)
    private String projectName;

    @Column(length = 2000)
    private String projectDescription;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "project_details")
    private JsonNode projectDetails;

}

