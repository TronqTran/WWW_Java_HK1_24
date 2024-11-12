package vn.edu.iuh.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.backend.dtos.JobSkillDto;
import vn.edu.iuh.backend.enums.SkillLevel;
import vn.edu.iuh.backend.models.Job;
import vn.edu.iuh.backend.models.JobSkill;
import vn.edu.iuh.backend.models.Skill;
import vn.edu.iuh.backend.repositories.JobSkillRepository;
import vn.edu.iuh.backend.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobSkillService {
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private SkillRepository skillRepository;

    public List<JobSkill> findByJobId(Long jobId) {
        return jobSkillRepository.findByJob_Id(jobId);
    }

    @Transactional
    public List<JobSkillDto> findAll () {
        List<JobSkillDto> jobSkillDtos = new ArrayList<>();
        List<JobSkill> jobSkills = jobSkillRepository.findAll();
        jobSkills.forEach(jobSkill -> {
            Job job = jobSkill.getJob();
            Skill skill = jobSkill.getSkill();
            JobSkillDto.JobSkillIdDto jobSkillIdDto = new JobSkillDto.JobSkillIdDto(
                    job.getId(),
                    skill.getId()
            );
            JobSkillDto.JobDto jobDto = new JobSkillDto.JobDto(
                    job.getId(),
                    job.getJobDesc(),
                    job.getJobName()
            );
            JobSkillDto.SkillDto skillDto = new JobSkillDto.SkillDto(
                    skill.getId(),
                    skill.getSkillName(),
                    skill.getSkillDescription(),
                    skill.getType()
            );
            JobSkillDto jobSkillDto = new JobSkillDto(jobSkillIdDto, jobDto, skillDto, jobSkill.getMoreInfos(), jobSkill.getSkillLevel());
            jobSkillDtos.add(jobSkillDto);
        });
        return jobSkillDtos;
    }

    @Transactional
    public Page<JobSkillDto> findByJob(Long jobId, int pageNo, int pageSize, String sortBy, String sortDirection) {
        List<JobSkillDto> jobSkillDtos = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        Page<JobSkill> jobSkillsPage = jobSkillRepository.findByJob_Id(jobId, pageable);
        List<JobSkill> jobSkills = jobSkillsPage.getContent();
        jobSkills.forEach(jobSkill -> {
            JobSkillDto.JobSkillIdDto jobSkillIdDto = new JobSkillDto.JobSkillIdDto(
                    jobSkill.getJob().getId(),
                    jobSkill.getSkill().getId()
            );
            JobSkillDto.JobDto jobDto = new JobSkillDto.JobDto(
                    jobSkill.getJob().getId(),
                    jobSkill.getJob().getJobDesc(),
                    jobSkill.getJob().getJobName()
            );
            JobSkillDto.SkillDto skillDto = new JobSkillDto.SkillDto(
                    jobSkill.getSkill().getId(),
                    jobSkill.getSkill().getSkillName(),
                    jobSkill.getSkill().getSkillDescription(),
                    jobSkill.getSkill().getType()
            );
            JobSkillDto jobSkillDto = new JobSkillDto(jobSkillIdDto, jobDto, skillDto, jobSkill.getMoreInfos(), jobSkill.getSkillLevel());
            jobSkillDtos.add(jobSkillDto);
        });
        return new PageImpl<>(jobSkillDtos, pageable, jobSkillsPage.getTotalElements());
    }
}
