package com.profiles.candiadate.service;

import com.profiles.candiadate.entity.Candidate;
import com.profiles.candiadate.enums.CandidateStatus;
import com.profiles.candiadate.repository.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate updateCandidate(Long id, Candidate candidate) {
        Candidate c = candidateRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Candidate not found"));

        c.setName(candidate.getName());
        c.setSkills(candidate.getSkills());
        c.setPrimarySkill(candidate.getPrimarySkill());
//      c.setStatus(candidate.getStatus());
        c.setYoe(candidate.getYoe());
        c.setLocationPref(candidate.getLocationPref());

    return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    public int bulkDeleteRejectedAfter6Months() {
        LocalDateTime cutoff = LocalDateTime.now().minusMonths(6);
        return candidateRepository.deleteRejectedOlderThan(cutoff);
    }

}
