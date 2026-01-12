package com.profiles.candiadate.controller;

import com.profiles.candiadate.entity.Candidate;
import com.profiles.candiadate.service.CandidateService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService service;

    public CandidateController(CandidateService candidateService) {
        this.service = candidateService;
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        return ResponseEntity.ok(service.createCandidate(candidate));
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(service.getAllCandidates());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate (@PathVariable Long id,
                                                      @RequestBody Candidate candidate) {
        return ResponseEntity.ok(service.updateCandidate(id,candidate));
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate (@PathVariable Long id) {
        service.deleteCandidate(id);
    }

    /*Restricvtive access*/
    @DeleteMapping("/cleanup/rejectedCandidates")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<String> candidateListCleanUp() {
        int count = service.bulkDeleteRejectedAfter6Months();
        return ResponseEntity.ok(count+" candidats cleaned up");
    }

}
