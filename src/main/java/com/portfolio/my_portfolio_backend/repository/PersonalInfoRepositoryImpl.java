package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, rowNum) -> {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setId(rs.getLong("id"));
        personalInfo.setFirstName(rs.getString("first_name"));
        personalInfo.setLastName(rs.getString("last_name"));
        personalInfo.setTitle(rs.getString("title"));
        personalInfo.setProfileDescription(rs.getString("profile_description"));
        personalInfo.setProfileImageUrl(rs.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(rs.getObject("years_of_experience", Integer.class));
        personalInfo.setEmail(rs.getString("email"));
        personalInfo.setPhone(rs.getString("phone"));
        personalInfo.setLinkedinUrl(rs.getString("linkedin_url"));
        personalInfo.setGithubUrl(rs.getString("github_url"));

        return personalInfo;
    };

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        if (personalInfo.getId() == null) {
            String sql = "INSERT INTO personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

                preparedStatement.setString(1, personalInfo.getFirstName());
                preparedStatement.setString(2, personalInfo.getLastName());
                preparedStatement.setString(3, personalInfo.getTitle());
                preparedStatement.setString(4, personalInfo.getProfileDescription());
                preparedStatement.setString(5, personalInfo.getProfileImageUrl());

                if (personalInfo.getYearsOfExperience() != null) {
                    preparedStatement.setObject(6, personalInfo.getYearsOfExperience());
                } else {
                    preparedStatement.setNull(6, java.sql.Types.INTEGER);
                }

                preparedStatement.setString(7, personalInfo.getEmail());
                preparedStatement.setString(8, personalInfo.getPhone());
                preparedStatement.setString(9, personalInfo.getLinkedinUrl());
                preparedStatement.setString(10, personalInfo.getGithubUrl());

                return preparedStatement;
            }, keyHolder);

            personalInfo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE personal_info SET first_name = ?, last_name = ?, title = ?, profile_description = ?, profile_image_url = ?, years_of_experience = ?, email = ?, phone = ?, linkedin_url = ?, github_url = ? WHERE id = ?";

            jdbcTemplate.update(sql, personalInfo.getFirstName(), personalInfo.getLastName(), personalInfo.getTitle(), personalInfo.getProfileDescription(), personalInfo.getProfileImageUrl(), personalInfo.getYearsOfExperience(), personalInfo.getEmail(), personalInfo.getPhone(), personalInfo.getLinkedinUrl(), personalInfo.getGithubUrl(), personalInfo.getId());
        }

        return personalInfo;
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        String sql = "SELECT * FROM personal_info WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "SELECT * FROM personal_info";

        return jdbcTemplate.query(sql, personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM personal_info WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }
}
