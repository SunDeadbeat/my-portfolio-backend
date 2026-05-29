package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.service.IEducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/educations")
@RequiredArgsConstructor
public class EducationController {
    private final IEducationService educationService;

    @GetMapping
    public List<Education> findAll()
    {
        return educationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Education> findById(@PathVariable Long id)
    {
        return educationService.findById(id);
    }

    @PostMapping
    public Education save(@RequestBody Education education)
    {
        return educationService.save(education);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id)
    {
        educationService.deleteById(id);
    }

    @GetMapping("/personal-info/{personalInfoId}")
    public List<Education> findByPersonalInfoId(@PathVariable Long personalInfoId)
    {
        return educationService.findByPersonalInfoId(personalInfoId);
    }
}
