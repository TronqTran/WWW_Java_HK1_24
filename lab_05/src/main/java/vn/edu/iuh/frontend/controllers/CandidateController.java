package vn.edu.iuh.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.backend.dtos.CandidateSkillDto;
import vn.edu.iuh.backend.models.Address;
import vn.edu.iuh.backend.models.Candidate;
import vn.edu.iuh.backend.models.JobSkill;
import vn.edu.iuh.backend.repositories.CandidateRepository;
import vn.edu.iuh.backend.services.AddressService;
import vn.edu.iuh.backend.services.CandidateService;
import vn.edu.iuh.backend.services.CandidateSkillService;
import vn.edu.iuh.backend.services.JobSkillService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private JobSkillService jobSkillService;

    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }

    @GetMapping("/create")
    public ModelAndView createCandidate() {
        ModelAndView view = new ModelAndView("candidates/create-candidate");
        String[] countryCodes = Arrays.stream(CountryCode.values()).map(CountryCode::getAlpha2).toArray(String[]::new);
        view.addObject("countryCodes", countryCodes);
        view.addObject("candidate", new Candidate());
        return view;
    }


    @PostMapping("/save")
    public ModelAndView saveCandidate(@ModelAttribute("candidate") Candidate candidate,
                                      @ModelAttribute("address") Address address,
                                      @RequestParam("dob") String dob) {
        ModelAndView view = new ModelAndView("candidates/create-candidate");
        addressService.save(address);
        candidate.setAddress(address);
        candidate.setDob(LocalDate.parse(dob));
        candidateService.save(candidate);
        return view;
    }

    @GetMapping
    public ModelAndView showCandidateListPaging (@RequestParam("page") Optional<Integer> page,
                                                 @RequestParam("size") Optional<Integer> size) {
        ModelAndView view = new ModelAndView("candidates/candidates-paging");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        Page<Candidate> candidatePage = candidateService.findAll(currentPage - 1, pageSize, "id", "asc");

        view.addObject("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        return view;
    }

    @GetMapping("/find-candidate/{jobId}")
    public ModelAndView findCandidateByJobSkill(@PathVariable("jobId") Long jobId,
                                                @RequestParam("page") Optional<Integer> page,
                                                @RequestParam("size") Optional<Integer> size) {
        ModelAndView view = new ModelAndView("candidates/job-candidate");

        //Danh sách các kỹ năng của công việc
        List<JobSkill> jobSkills = jobSkillService.findByJobId(jobId);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);

        //Lấy tất cả các công việc
        List<CandidateSkillDto> allCandidateSkills = candidateSkillService.findAll();

        //Tạo danh sách để lưu các ứng viên phù hợp
        List<CandidateSkillDto> matchingCandidates = new ArrayList<>();

        //Lặp qua từng ứng viên
        Map<Long, List<CandidateSkillDto>> candidateSkillsMap = new HashMap<>();
        for (CandidateSkillDto candidateSkill : allCandidateSkills) {
           candidateSkillsMap.computeIfAbsent(candidateSkill.getCan().getId(), k -> new ArrayList<>()).add(candidateSkill);
        }

        //Kiểm tra từng ứng viên
        for (Map.Entry<Long, List<CandidateSkillDto>> entry : candidateSkillsMap.entrySet()) {
            Long candidateId = entry.getKey();
            List<CandidateSkillDto> candidateSkills = entry.getValue();

            boolean isCandidateMatch = true;

            //Lặp các kỹ năng của ứng viên
            for (CandidateSkillDto candidateSkillDto : candidateSkills) {
                boolean isSkillMatch = false;
                //Xem xét từng kỹ năng của công việc
                for (JobSkill jobSkill : jobSkills) {
                    if (candidateSkillDto.getSkill().getId().equals(jobSkill.getSkill().getId()) &&
                            candidateSkillDto.getSkillLevel().equals(jobSkill.getSkillLevel())) {
                        isSkillMatch = true;
                        break;
                    }
                }

                if (!isSkillMatch) {
                    isCandidateMatch = false;
                    break;
                }
            }

            if (isCandidateMatch) {
                matchingCandidates.addAll(candidateSkills);
            }
        }

        int start = Math.min((currentPage -1) * pageSize, matchingCandidates.size());
        int end = Math.min(start + pageSize, matchingCandidates.size());
        Page<CandidateSkillDto> candidateSkillDtoPage = new PageImpl<>(matchingCandidates.subList(start, end),
                PageRequest.of(currentPage - 1, pageSize),
                matchingCandidates.size());

        view.addObject("candidateSkillPage", candidateSkillDtoPage);
        int totalPages = candidateSkillDtoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        view.addObject("jobId", jobId);

        return view;
    }
}
