package vn.edu.iuh.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.backend.dtos.CandidateSkillDto;
import vn.edu.iuh.backend.dtos.JobSkillDto;
import vn.edu.iuh.backend.enums.SkillLevel;
import vn.edu.iuh.backend.models.*;
import vn.edu.iuh.backend.services.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private JobSkillService jobSkillService;
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CandidateService candidateService;


    @GetMapping
    public ModelAndView findAll (@RequestParam("size") Optional<Integer> size,
                                 @RequestParam("page") Optional<Integer> page) {
        ModelAndView view  = new ModelAndView("skills/skills");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        Page<Skill> skillPages = skillService.findAll(currentPage - 1, pageSize, "id", "asc");
        view.addObject("skillPages", skillPages);
        int totalPages = skillPages.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        return view;
    }

    @GetMapping("/addSkill/{candidateId}")
    public ModelAndView addSkill(@PathVariable("candidateId") Long candidateId) {
        ModelAndView view = new ModelAndView("skills/add-skill");
        List<Skill> skills = skillService.findAll();
        Candidate candidate = candidateService.findById(candidateId);
        view.addObject("candidate", candidate);
        view.addObject("skills", skills);
        return view;
    }

    @PostMapping("/addSkill")
    public ModelAndView addSkill(@RequestParam("candidateId") Long candidateId,
                                 @RequestParam("skillId") Long skillId,
                                 @ModelAttribute("candidateSkill") CandidateSkill candidateSkill) {

        Candidate candidate = candidateService.findById(candidateId);
        Skill skill = skillService.findById(skillId);

        CandidateSkillId candidateSkillId = new CandidateSkillId();
        candidateSkillId.setCanId(candidate.getId());
        candidateSkillId.setSkillId(skill.getId());
        candidateSkill.setId(candidateSkillId);
        candidateSkill.setCan(candidate);
        candidateSkill.setSkill(skill);

        candidateSkillService.save(candidateSkill);

        ModelAndView modelAndView = new ModelAndView("redirect:/skills/candidate/" + candidateId);
        return modelAndView;
    }



    @GetMapping("/job/{jobId}")
    public ModelAndView findByJob(@PathVariable("jobId") Optional<Long> jobId, @RequestParam("size") Optional<Integer> size, @RequestParam("page") Optional<Integer> page) {
        ModelAndView view = new ModelAndView("skills/job-skill");
        Long id = jobId.get();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        Page<JobSkillDto> jobSkillPages = jobSkillService.findByJob(id, currentPage - 1, pageSize, "id", "asc");
        view.addObject("jobSkillPages", jobSkillPages);
        view.addObject("jobId", id);
        int totalPages = jobSkillPages.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        return view;
    }

    @GetMapping("/candidate/{candidateId}")
    public ModelAndView findByCandidate(@PathVariable("candidateId") Optional<Long> candidateId, @RequestParam("size") Optional<Integer> size, @RequestParam("page") Optional<Integer> page) {
        ModelAndView view = new ModelAndView("skills/candidate-skill");
        Long id = candidateId.get();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        Page<CandidateSkillDto> jobSkillPages = candidateSkillService.findByCandidate(id, currentPage - 1, pageSize, "id", "asc");
        view.addObject("candidateSkillPages", jobSkillPages);
        view.addObject("candidateId", id);
        int totalPages = jobSkillPages.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        return view;
    }

}
