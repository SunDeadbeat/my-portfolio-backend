package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Skill;
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
public class SkillRepositoryImpl implements ISkillRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Skill> skillRowMapper = (rs,  rowNum) -> {
        Skill skill = new Skill();

        skill.setId(rs.getLong("id"));
        skill.setName(rs.getString("name"));
        skill.setLevelPercentage(rs.getString("level_percentage"));
        skill.setIconClass(rs.getString("icon_class"));
        skill.setPersonalInfoId(rs.getLong("personal_info_id"));

        return skill;
    };

    @Override
    public Skill save(Skill skill) {
        if (skill.getId() == null) {
            String sql = "INSERT INTO skill (name, level_percentage, icon_class, personal_info_id) VALUES (?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

                preparedStatement.setString(1, skill.getName());
                preparedStatement.setString(2, skill.getLevelPercentage());
                preparedStatement.setString(3, skill.getIconClass());
                preparedStatement.setLong(4, skill.getPersonalInfoId());

                return  preparedStatement;
            }, keyHolder);

            skill.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE skill SET name = ?, level_percentage = ?, icon_class = ?, personal_info_id = ? WHERE id = ?";

            jdbcTemplate.update(sql, skill.getName(), skill.getLevelPercentage(), skill.getIconClass(), skill.getPersonalInfoId(), skill.getId());
        }

        return skill;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        String  sql = "SELECT * FROM skill WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, skillRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String sql = "SELECT * FROM skill";

        return jdbcTemplate.query(sql, skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM skill WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Skill> findByPersonalInfoId(Long id) {
        String  sql = "SELECT * FROM skill WHERE personal_info_id = ?";

        return jdbcTemplate.query(sql, skillRowMapper, id);
    }
}
