package com.profiles.candiadate.entity;

import com.profiles.candiadate.enums.CandidateStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateId;
    private String name;

    @ElementCollection
    @CollectionTable(name="candidate_skills", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name="skill")
    private List<String> skills;
    private String primarySkill;
    private int yoe;
    private String locationPref;

    @Enumerated(EnumType.STRING)
    private CandidateStatus status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private String department;

    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.candidateId = id;
    }

    public Long getId() {
        return candidateId;
    }
}
