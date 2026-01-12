package com.profiles.candiadate.entity;

import com.profiles.candiadate.enums.CandidateStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "candidates")
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

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public int getYoe() {
        return yoe;
    }

    public void setYoe(int yoe) {
        this.yoe = yoe;
    }

    public String getLocationPref() {
        return locationPref;
    }

    public void setLocationPref(String locationPref) {
        this.locationPref = locationPref;
    }

    public CandidateStatus getStatus(CandidateStatus status) {
        return this.status;
    }

    public void setStatus(CandidateStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private String department;

    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }



}
