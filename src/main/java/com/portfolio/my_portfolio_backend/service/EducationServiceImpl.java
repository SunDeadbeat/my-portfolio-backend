package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducationService{
    private final IEducationRepository educationRepository;

    @Override
    public Education save(Education education) {
        return educationRepository.save(education);
    }

    @Override
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }

    @Override
    public List<Education> findByPersonalInfoId(Long id) {
        return educationRepository.findByPersonalInfoId(id);
    }
}
