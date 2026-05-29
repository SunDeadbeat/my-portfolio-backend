package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    Project save(Project project);
    Optional<Project> findById(Long id);
    List<Project> findAll();
    void  deleteById(Long id);
    List<Project> findByPersonalInfoId(Long personalInfoId);
}
