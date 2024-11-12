package vn.edu.iuh.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.backend.enums.SkillLevel;
import vn.edu.iuh.backend.models.JobSkill;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
    Page<JobSkill> findByJob_Id(Long jobId, Pageable pageable);
    List<JobSkill> findByJob_Id(Long jobId);
}
