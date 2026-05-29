package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Experience;
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
public class ExperienceRepositoryImpl implements IExperienceRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Experience> experienceRowMapper = (rs, rowNum) -> {
        Experience experience = new Experience();

        experience.setId(rs.getLong("id"));
        experience.setJobTitle(rs.getString("job_title"));
        experience.setCompanyName(rs.getString("company_name"));
        experience.setStartDate(rs.getObject("start_date", java.time.LocalDate.class));
        experience.setEndDate(rs.getObject("end_date", java.time.LocalDate.class));
        experience.setDescription(rs.getString("description"));
        experience.setPersonalInfoId(rs.getLong("personal_info_id"));

        return experience;
    };

    @Override
    public Experience save(Experience experience) {
        if (experience.getId() == null) {
            String sql = "INSERT INTO experience (job_title, company_name, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
               PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

               preparedStatement.setString(1, experience.getJobTitle());
               preparedStatement.setString(2, experience.getCompanyName());
               preparedStatement.setObject(3, experience.getStartDate());
               preparedStatement.setObject(4, experience.getEndDate());
               preparedStatement.setString(5, experience.getDescription());
               preparedStatement.setLong(6, experience.getPersonalInfoId());

               return preparedStatement;
            }, keyHolder);

            experience.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE experience SET job_title = ?, company_name = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";

            jdbcTemplate.update(sql, experience.getJobTitle(), experience.getCompanyName(), experience.getStartDate(), experience.getEndDate(), experience.getDescription(), experience.getPersonalInfoId(), experience.getId());
        }

        return experience;
    }

    @Override
    public Optional<Experience> findById(Long id) {
        String sql = "SELECT * FROM experience WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, experienceRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Experience> findAll() {
        String sql = "SELECT * FROM experience";

        return jdbcTemplate.query(sql, experienceRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM experience WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Experience> findByPersonalInfoId(Long id) {
        String sql = "SELECT * FROM experience WHERE personal_info_id = ?";

        return jdbcTemplate.query(sql, experienceRowMapper, id);
    }
}
