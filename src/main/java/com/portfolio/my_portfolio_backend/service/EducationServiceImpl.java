package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducationService{
    private final IEducationService educationService;

    @Override
    public Education save(Education education) {
        return educationService.save(education);
    }

    @Override
    public Optional<Education> findById(Long id) {
        return educationService.findById(id);
    }

    @Override
    public List<Education> findAll() {
        return educationService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        educationService.deleteById(id);
    }

    @Override
    public List<Education> findByPersonalInfoId(Long id) {
        return educationService.findByPersonalInfoId(id);
    }
}
