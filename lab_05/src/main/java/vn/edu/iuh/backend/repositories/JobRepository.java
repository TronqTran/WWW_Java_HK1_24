package vn.edu.iuh.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.backend.models.Company;
import vn.edu.iuh.backend.models.Job;

import java.nio.CharBuffer;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByCompany(Company company, Pageable pageable);
}
