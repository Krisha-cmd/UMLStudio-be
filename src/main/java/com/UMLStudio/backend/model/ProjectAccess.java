package com.UMLStudio.backend.model;

import java.time.LocalDateTime;

import com.UMLStudio.backend.Utils.AccessPolicy;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "project_access",
    uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "projectId"})
)
public class ProjectAccess {

    public ProjectAccess(Long userId, Long projectId, AccessPolicy accessPolicy) {
        this.userId=userId;
        this.projectId=projectId;
        this.accessPolicy=accessPolicy;
    }

    public ProjectAccess(Long projectId, Long userId, LocalDateTime assignedAt, AccessPolicy accessPolicy) {
        this.accessPolicy=accessPolicy;
        this.assignedAt=assignedAt;
        this.projectId=projectId;
        this.userId=userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long projectId;

    @Enumerated(EnumType.STRING)
    private AccessPolicy accessPolicy;

    private LocalDateTime assignedAt;
}
