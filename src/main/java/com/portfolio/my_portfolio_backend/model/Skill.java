package com.portfolio.my_portfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Long id;
    private String name;
    private String levelPercentage;
    private String iconClass;
    private Long personalInfoId;
}
