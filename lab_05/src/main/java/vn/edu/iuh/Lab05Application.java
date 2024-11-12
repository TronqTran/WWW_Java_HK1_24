package vn.edu.iuh;

import com.neovisionaries.i18n.CountryCode;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.backend.dtos.JobSkillDto;
import vn.edu.iuh.backend.enums.SkillLevel;
import vn.edu.iuh.backend.enums.SkillType;
import vn.edu.iuh.backend.models.*;
import vn.edu.iuh.backend.repositories.*;
import vn.edu.iuh.backend.services.CandidateSkillService;
import vn.edu.iuh.backend.services.JobService;
import vn.edu.iuh.backend.services.JobSkillService;
import vn.edu.iuh.frontend.controllers.CandidateController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
public class Lab05Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab05Application.class, args);
    }

    /*data for testing*/
//    @Autowired
//    private AddressRepository addressRepository;
//    @Autowired
//    private CompanyRepository companyRepository;
//    @Autowired
//    private CandidateRepository candidateRepository;
//    @Autowired
//    private SkillRepository skillRepository;
//    @Autowired
//    private JobRepository jobRepository;
//    @Autowired
//    private JobSkillRepository jobSkillRepository;
//    @Autowired
//    private CandidateSkillRepository candidateSkillRepository;
//
//    @Bean
//    CommandLineRunner runner (){
//        return args -> {
//            Random random = new Random();
//            Faker faker = new Faker();
//            for (int i = 1; i < 100; i++){
//
//                Address address = new Address();
//                address.setCity(faker.address().city());
//                address.setCountry(CountryCode.VN);
//                address.setStreet(faker.address().streetName());
//                address.setNumber(faker.address().streetAddressNumber());
//                address.setZipcode(faker.address().zipCode());
//                addressRepository.save(address);
//
//                Company company = new Company();
//                company.setAddress(address);
//                company.setCompName(faker.company().name());
//                company.setEmail(faker.internet().emailAddress());
//                company.setPhone(faker.phoneNumber().phoneNumber());
//                company.setAbout(faker.company().catchPhrase());
//                company.setWebUrl(faker.internet().url());
//                companyRepository.save(company);
//
//                Candidate candidate = new Candidate();
//                candidate.setFullName(faker.name().fullName());
//                candidate.setAddress(address);
//                candidate.setDob(LocalDate.of(1990 + random.nextInt(30), 1 + random.nextInt(12), 1 + random.nextInt(28)));
//                candidate.setPhone(faker.phoneNumber().phoneNumber());
//                candidate.setEmail(faker.internet().emailAddress());
//                candidateRepository.save(candidate);
//
//                for (int j = 1; j < 10; j++){
//                    Job job = new Job();
//                    job.setCompany(company);
//                    job.setJobName(faker.job().title());
//                    job.setJobDesc(faker.lorem().sentence());
//                    jobRepository.save(job);
//                }
//            }
//            for (int i = 1; i < 3; i++){
//                Skill skill = new Skill();
//                skill.setSkillName(faker.job().keySkills());
//                skill.setSkillDescription(faker.lorem().sentence());
//                skill.setType(SkillType.TECHNICAL_SKILL);
//                skillRepository.save(skill);
//            }
//
//            for (int i = 1; i < 3; i++){
//                Skill skill = new Skill();
//                skill.setSkillName(faker.job().keySkills());
//                skill.setSkillDescription(faker.lorem().sentence());
//                skill.setType(SkillType.SOFT_SKILL);
//                skillRepository.save(skill);
//            }
//
//            for (int i = 1; i < 3; i++) {
//                Skill skill = new Skill();
//                skill.setSkillName(faker.job().keySkills());
//                skill.setSkillDescription(faker.lorem().sentence());
//                skill.setType(SkillType.UNSPECIFIED);
//                skillRepository.save(skill);
//            }
//
//            for (int i = 1; i < 892; i++) {
//                Job job = jobRepository.findById((long) i).get();
//                Skill skill = skillRepository.findById((long) random.nextInt(5)+1).get();
//                JobSkillId jobSkillId = new JobSkillId();
//                jobSkillId.setJobId(job.getId());
//                jobSkillId.setSkillId(skill.getId());
//                JobSkill jobSkill = new JobSkill();
//                jobSkill.setId(jobSkillId);
//                jobSkill.setJob(job);
//                jobSkill.setSkill(skill);
//                jobSkill.setMoreInfos(faker.lorem().sentence());
//                jobSkill.setSkillLevel(SkillLevel.values()[random.nextInt(5)]);
//                jobSkillRepository.save(jobSkill);
//            }
//
//            for (int i = 1; i < 100; i++){
//                Candidate candidate = candidateRepository.findById((long) i).get();
//                Skill skill = skillRepository.findById((long) random.nextInt(5)+1).get();
//                CandidateSkillId candidateSkillId = new CandidateSkillId();
//                candidateSkillId.setCanId(candidate.getId());
//                candidateSkillId.setSkillId(skill.getId());
//                CandidateSkill candidateSkill = new CandidateSkill();
//                candidateSkill.setId(candidateSkillId);
//                candidateSkill.setCan(candidate);
//                candidateSkill.setSkill(skill);
//                candidateSkill.setSkillLevel(SkillLevel.values()[random.nextInt(5)]);
//                candidateSkill.setMoreInfos(faker.lorem().sentence());
//                candidateSkillRepository.save(candidateSkill);
//            }
//
//        };
//    }
}

