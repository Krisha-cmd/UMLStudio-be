package com.UMLStudio.backend.model;

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
    
    public ProjectAccess(Long userId2, Long projectId2, AccessPolicy viewer) {
        //TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long projectId;

    @Enumerated(EnumType.STRING)
    private AccessPolicy accessPolicy;
}
