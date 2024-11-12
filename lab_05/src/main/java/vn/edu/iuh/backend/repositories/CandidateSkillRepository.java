package vn.edu.iuh.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.backend.models.CandidateSkill;

import java.util.List;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
    List<CandidateSkill> findByCan_Id(Long canId);
    Page<CandidateSkill> findByCan_Id(Long canId, Pageable pageable);
}
