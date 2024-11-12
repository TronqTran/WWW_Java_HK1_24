package vn.edu.iuh.backend.dtos;

import lombok.Value;
import vn.edu.iuh.backend.enums.SkillLevel;
import vn.edu.iuh.backend.enums.SkillType;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.backend.models.JobSkill}
 */
@Value
public class JobSkillDto implements Serializable {
    JobSkillIdDto id;
    JobDto job;
    SkillDto skill;
    String moreInfos;
    SkillLevel skillLevel;

    /**
     * DTO for {@link vn.edu.iuh.backend.models.JobSkillId}
     */
    @Value
    public static class JobSkillIdDto implements Serializable {
        Long jobId;
        Long skillId;
    }

    /**
     * DTO for {@link vn.edu.iuh.backend.models.Job}
     */
    @Value
    public static class JobDto implements Serializable {
        Long id;
        String jobDesc;
        String jobName;
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