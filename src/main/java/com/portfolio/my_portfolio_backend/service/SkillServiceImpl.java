package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService {
    private final ISkillService skillService;

    @Override
    public Skill save(Skill skill) {
        return skillService.save(skill);
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return skillService.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        skillService.deleteById(id);
    }

    @Override
    public List<Skill> findByPersonalInfoId(Long id) {
        return  skillService.findByPersonalInfoId(id);
    }
}
