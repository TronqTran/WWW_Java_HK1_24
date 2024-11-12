package vn.edu.iuh.backend.dtos;

import lombok.Value;
import vn.edu.iuh.backend.enums.SkillLevel;
import vn.edu.iuh.backend.enums.SkillType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link vn.edu.iuh.backend.models.CandidateSkill}
 */
@Value
public class CandidateSkillDto implements Serializable {
    CandidateSkillIdDto id;
    CandidateDto can;
    SkillDto skill;
    String moreInfos;
    SkillLevel skillLevel;

    /**
     * DTO for {@link vn.edu.iuh.backend.models.CandidateSkillId}
     */
    @Value
    public static class CandidateSkillIdDto implements Serializable {
        Long canId;
        Long skillId;
    }

    /**
     * DTO for {@link vn.edu.iuh.backend.models.Candidate}
     */
    @Value
    public static class CandidateDto implements Serializable {
        Long id;
        String fullName;
        String email;
        LocalDate dob;
        String phone;
    }

    /**
     * DTO for {@link vn.edu.iuh.backend.models.Skill}
     */
    @Value
    public static class SkillDto implements Serializable {
        Long id;
        String skillDescription;
        String skillName;
        SkillType type;
    }
}