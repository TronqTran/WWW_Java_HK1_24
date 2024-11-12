package vn.edu.iuh.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.backend.models.Company;
import vn.edu.iuh.backend.services.CompanyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public ModelAndView findAll(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {
        ModelAndView view = new ModelAndView("companies/companies");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        Page<Company> companyPages = companyService.findAll(
                currentPage - 1,
                pageSize,
                "id",
                "asc");
        view.addObject("companyPages", companyPages);
        int totalPages = companyPages.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            view.addObject("pageNumbers", pageNumbers);
        }
        return view;
    }
}
