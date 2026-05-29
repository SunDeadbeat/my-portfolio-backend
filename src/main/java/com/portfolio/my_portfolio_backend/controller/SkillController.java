package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.service.ISkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    private final ISkillService skillService;

    @GetMapping()
    public List<Skill> getSkills() {
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Skill> getSkill(@PathVariable Long id) {
        return skillService.findById(id);
    }

    @PostMapping()
    public Skill save(@RequestBody Skill skill) {
        return skillService.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        skillService.deleteById(id);
    }

    @GetMapping("/personal-info/{personalInfoId}")
    public List<Skill> findByPersonalInfoId(@PathVariable Long personalInfoId) {
        return skillService.findByPersonalInfoId(personalInfoId);
    }
}
