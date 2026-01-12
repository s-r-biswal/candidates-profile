package com.profiles.candiadate.repository;

import com.profiles.candiadate.entity.Candidate;
import com.profiles.candiadate.enums.CandidateStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface  CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByStatus(CandidateStatus status);

    @Modifying
    @Transactional
    @Query("""
            DELETE FROM Candidate c
            WHERE c.status = 'REJECTED'
            AND c.createdAt < :cutoff
            """)
    int deleteRejectedOlderThan(@Param("cutoff")LocalDateTime cutoff);
}


