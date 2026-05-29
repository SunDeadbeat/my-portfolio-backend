package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.service.IExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceController {
    private final IExperienceService experienceService;

    @GetMapping
    public List<Experience> findAll()
    {
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Experience> findById(@PathVariable Long id)
    {
        return experienceService.findById(id);
    }

    @PostMapping
    public Experience save(@RequestBody Experience experience) {
        return experienceService.save(experience);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id)
    {
        experienceService.deleteById(id);
    }

    @GetMapping("/personal-info/{personalInfoId}")
    public List<Experience> findByPersonalInfoId(@PathVariable Long personalInfoId)
    {
        return experienceService.findByPersonalInfoId(personalInfoId);
    }
}
