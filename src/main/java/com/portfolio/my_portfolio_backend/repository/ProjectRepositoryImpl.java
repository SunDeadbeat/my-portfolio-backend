package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class ProjectRepositoryImpl implements IProjectRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {;
        Project project = new Project();

        project.setId(rs.getLong("id"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setImageUrl(rs.getString("image_url"));
        project.setProjectUrl(rs.getString("project_url"));
        project.setPersonalInfoId(rs.getLong("personal_info_id"));

        return project;
    };

    @Override
    public Project save(Project project) {
        if (project.getId() == null) {
            String sql = "INSERT INTO project (title, description, image_url, project_url, personal_info_id) VALUES (?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

                preparedStatement.setString(1, project.getTitle());
                preparedStatement.setString(2, project.getDescription());
                preparedStatement.setString(3, project.getImageUrl());
                preparedStatement.setString(4, project.getProjectUrl());
                preparedStatement.setLong(5, project.getPersonalInfoId());

                return preparedStatement;
            }, keyHolder);

            project.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE project SET title = ?, description = ?, image_url = ?, project_url = ?, personal_info_id = ? WHERE id = ?";

            jdbcTemplate.update(sql, project.getTitle(), project.getDescription(), project.getImageUrl(), project.getProjectUrl(), project.getPersonalInfoId(), project.getId());
        }

        return project;
    }

    @Override
    public Optional<Project> findById(Long id) {
        String sql = "SELECT * FROM project WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, projectRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Project> findAll() {
        String sql = "SELECT * FROM project";

        return jdbcTemplate.query(sql, projectRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM project WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Project> findByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT * FROM project WHERE personal_info_id = ?";

        return jdbcTemplate.query(sql, projectRowMapper, personalInfoId);
    }
}
