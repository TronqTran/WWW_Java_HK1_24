package vn.edu.iuh.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.backend.dtos.JobSkillDto;
import vn.edu.iuh.backend.models.CandidateSkill;
import vn.edu.iuh.backend.models.Company;
import vn.edu.iuh.backend.models.Job;
import vn.edu.iuh.backend.services.CandidateSkillService;
import vn.edu.iuh.backend.services.CompanyService;
import vn.edu.iuh.backend.services.JobService;
import vn.edu.iuh.backend.services.JobSkillService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private JobSkillService jobSkillService;

    @GetMapping
    public ModelAndView findAll(@RequestParam("size") Optional<Integer> size,
                                @RequestParam("page") Optional<Integer> page) {
        ModelAndView view = new ModelAndView("jobs/jobs");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        Page<Job> jobPages = jobService.findAll(currentPage - 1, pageSize, "id", "asc");
        view.addObject("jobPages", jobPages);
        int totalPages = jobPages.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        return view;
    }

    @GetMapping("/company/{id}")
    public ModelAndView findByCompany(@PathVariable("id") Optional<Long> id,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("page") Optional<Integer> page) {
        ModelAndView view = new ModelAndView("jobs/company-job");
        Long companyId = id.get();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        Page<Job> jobPages = jobService.findByCompany(companyId, currentPage - 1, pageSize, "id", "asc");
        Company company = companyService.findById(companyId);
        view.addObject("company", company);
        view.addObject("jobPages", jobPages);
        view.addObject("companyId", companyId);
        int totalPages = jobPages.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        return view;
    }
    @GetMapping("/find-job/{canId}")
    public ModelAndView findJobByCandidateSkill(@PathVariable("canId") Long canId,
                                                @RequestParam("page") Optional<Integer> page,
                                                @RequestParam("size") Optional<Integer> size) {
        ModelAndView view = new ModelAndView("jobs/candidate-job");

        // Lấy danh sách kỹ năng của ứng viên
        List<CandidateSkill> candidateSkills = candidateSkillService.findByCandidate(canId);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);

        // Lấy tất cả các công việc
        List<JobSkillDto> allJobSkills = jobSkillService.findAll(); // Giả sử bạn có phương thức này

        // Tạo danh sách để lưu trữ các công việc phù hợp
        List<JobSkillDto> matchingJobs = new ArrayList<>();

        // Lặp qua từng công việc
        Map<Long, List<JobSkillDto>> jobSkillsMap = new HashMap<>();
        for (JobSkillDto jobSkill : allJobSkills) {
            jobSkillsMap.computeIfAbsent(jobSkill.getJob().getId(), k -> new ArrayList<>()).add(jobSkill);
        }

        // Kiểm tra từng công việc
        for (Map.Entry<Long, List<JobSkillDto>> entry : jobSkillsMap.entrySet()) {
            Long jobId = entry.getKey();
            List<JobSkillDto> requiredSkills = entry.getValue();

            boolean allSkillsMatched = true; // Biến để kiểm tra xem tất cả kỹ năng có được đáp ứng không

            // Lặp qua tất cả các kỹ năng yêu cầu của công việc
            for (JobSkillDto requiredSkill : requiredSkills) {
                boolean skillMatched = false; // Biến để kiểm tra xem kỹ năng hiện tại có được ứng viên đáp ứng không

                // Kiểm tra xem ứng viên có kỹ năng này không
                for (CandidateSkill candidateSkill : candidateSkills) {
                    if (requiredSkill.getId().getSkillId().equals(candidateSkill.getId().getSkillId()) &&
                            requiredSkill.getSkillLevel().equals(candidateSkill.getSkillLevel())) {
                        skillMatched = true; // Kỹ năng đã được ứng viên đáp ứng
                        break; // Không cần kiểm tra thêm
                    }
                }

                // Nếu có bất kỳ kỹ năng nào không được đáp ứng, đánh dấu là không phù hợp
                if (!skillMatched) {
                    allSkillsMatched = false;
                    break; // Không cần kiểm tra thêm kỹ năng yêu cầu cho công việc này
                }
            }

            // Nếu ứng viên đáp ứng tất cả các kỹ năng yêu cầu, thêm công việc vào danh sách
            if (allSkillsMatched) {
                matchingJobs.addAll(requiredSkills); // Thêm tất cả các kỹ năng của công việc vào danh sách
            }
        }

        // Phân trang
        int start = Math.min((currentPage - 1) * pageSize, matchingJobs.size());
        int end = Math.min(start + pageSize, matchingJobs.size());
        Page<JobSkillDto> jobSkillDtoPage = new PageImpl<>(matchingJobs.subList(start, end),
                PageRequest.of(currentPage - 1, pageSize),
                matchingJobs.size());

        // Thêm thông tin vào view
        view.addObject("jobSkillDtoPage", jobSkillDtoPage);
        int totalPages = jobSkillDtoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        view.addObject("canId", canId);

        return view;
    }
}
