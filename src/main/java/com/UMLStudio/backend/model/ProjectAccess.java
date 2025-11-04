package com.UMLStudio.backend.model;

import java.time.LocalDateTime;

import com.UMLStudio.backend.Utils.AccessPolicy;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAccess {

    public ProjectAccess(Long userId, Long projectId, AccessPolicy viewer) {
        this.userId=userId;
        this.projectId=projectId;
        this.accessPolicy=viewer;
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
