package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.model.Project;
import com.portfolio.my_portfolio_backend.service.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final IProjectService projectService;

    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Project> findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    public Project save(@RequestBody Project project) {
        return projectService.save(project);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        projectService.deleteById(id);
    }

    @GetMapping("/personal-info/{personalInfoId}")
    public List<Project> findByPersonalInfoId(@PathVariable Long personalInfoId) {
        return projectService.findByPersonalInfoId(personalInfoId);
    }
}
