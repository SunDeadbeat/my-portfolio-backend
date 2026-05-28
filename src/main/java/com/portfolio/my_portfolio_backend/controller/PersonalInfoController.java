package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-info")
@AllArgsConstructor
public class PersonalInfoController {
    private final IPersonalInfoService personalInfoService;

    @GetMapping()
    public List<PersonalInfo> findAll(){
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PersonalInfo> findById(@PathVariable Long id){
        return personalInfoService.findById(id);
    }

    @PostMapping()
    public PersonalInfo save(@RequestBody PersonalInfo personalInfo){
        return personalInfoService.save(personalInfo);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        personalInfoService.deleteById(id);
    }
}
