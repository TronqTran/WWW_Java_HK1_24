package vn.edu.iuh.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.backend.models.Candidate;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
