package vn.edu.iuh.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.backend.dtos.CandidateSkillDto;
import vn.edu.iuh.backend.dtos.JobSkillDto;
import vn.edu.iuh.backend.models.Candidate;
import vn.edu.iuh.backend.models.CandidateSkill;
import vn.edu.iuh.backend.repositories.CandidateSkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateSkillService {
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    public CandidateSkill save(CandidateSkill candidateSkill) {
        return candidateSkillRepository.save(candidateSkill);
    }

    public List<CandidateSkillDto> findAll() {
        List<CandidateSkillDto> candidateSkillDtos = new ArrayList<>();
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findAll();
        for (CandidateSkill candidateSkill : candidateSkills) {
            CandidateSkillDto.CandidateSkillIdDto candidateSkillIdDto = new CandidateSkillDto.CandidateSkillIdDto(
                    candidateSkill.getCan().getId(),
                    candidateSkill.getSkill().getId()
            );
            CandidateSkillDto.CandidateDto candidateDto =new CandidateSkillDto.CandidateDto(
                    candidateSkill.getCan().getId(),
                    candidateSkill.getCan().getFullName(),
                    candidateSkill.getCan().getEmail(),
                    candidateSkill.getCan().getDob(),
                    candidateSkill.getCan().getPhone()
            );
            CandidateSkillDto.SkillDto skillDto = new CandidateSkillDto.SkillDto(
                    candidateSkill.getSkill().getId(),
                    candidateSkill.getSkill().getSkillDescription(),
                    candidateSkill.getSkill().getSkillName(),
                    candidateSkill.getSkill().getType()
            );
            CandidateSkillDto candidateSkillDto = new CandidateSkillDto(candidateSkillIdDto, candidateDto, skillDto, candidateSkill.getMoreInfos(), candidateSkill.getSkillLevel());
            candidateSkillDtos.add(candidateSkillDto);
        }
        return candidateSkillDtos;
    }

    public List<CandidateSkill> findByCandidate(Long canId) {
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findByCan_Id(canId);
        return candidateSkills;
    }

    @Transactional
    public Page<CandidateSkillDto> findByCandidate(Long canId, int pageNo, int pageSize, String sortBy, String sortDirection) {
        List<CandidateSkillDto> candidateSkillDtos = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<CandidateSkill> candidateSkills = candidateSkillRepository.findByCan_Id(canId, pageable);
        for (CandidateSkill candidateSkill : candidateSkills.getContent()) {
            CandidateSkillDto.CandidateSkillIdDto candidateSkillIdDto = new CandidateSkillDto.CandidateSkillIdDto(
                    candidateSkill.getCan().getId(),
                    candidateSkill.getSkill().getId()
            );
            CandidateSkillDto.CandidateDto candidateDto =new CandidateSkillDto.CandidateDto(
                    candidateSkill.getCan().getId(),
                    candidateSkill.getCan().getFullName(),
                    candidateSkill.getCan().getEmail(),
                    candidateSkill.getCan().getDob(),
                    candidateSkill.getCan().getPhone()
            );
            CandidateSkillDto.SkillDto skillDto = new CandidateSkillDto.SkillDto(
                    candidateSkill.getSkill().getId(),
                    candidateSkill.getSkill().getSkillDescription(),
                    candidateSkill.getSkill().getSkillName(),
                    candidateSkill.getSkill().getType()
            );
            CandidateSkillDto candidateSkillDto = new CandidateSkillDto(candidateSkillIdDto, candidateDto, skillDto, candidateSkill.getMoreInfos(), candidateSkill.getSkillLevel());
            candidateSkillDtos.add(candidateSkillDto);
        }
        return new PageImpl<>(candidateSkillDtos, pageable, candidateSkills.getTotalElements());
    }

}
