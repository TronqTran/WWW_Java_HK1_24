package vn.edu.iuh.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.backend.enums.SkillType;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "type")
    private SkillType type;

    @OneToMany(mappedBy = "skill")
    private Set<CandidateSkill> candidateSkills = new LinkedHashSet<>();

    @OneToMany(mappedBy = "skill", fetch = FetchType.EAGER)
    private Set<JobSkill> jobSkills = new LinkedHashSet<>();

    @Override
    public String toString() {
        return  "Skill{" +
                "id=" + id +
                ", skillDescription='" + skillDescription + '\'' +
                ", skillName='" + skillName + '\'' +
                ", type=" + type +
                '}';
    }
}