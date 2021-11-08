package ru.job4j.accident.repository;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {

    private static final Logger LOG = LogManager.getLogger(AccidentJdbcTemplate.class.getName());

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        if (accident.getId() != 0) {
            jdbc.update("update accident set name = ? where id = ?",
                    accident.getName(),
                    accident.getId());
        } else {
            jdbc.update("insert into accident (name, text, address, accident_type_id)"
                            + " values (?, ?, ?, ?)",
                    accident.getName(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getType().getId());
        }
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select * from accident order by id asc",
                (rs, row) -> {
                    Accident accident = new Accident();
                    int accidentTypeId = rs.getInt("accident_type_id");
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findAccidentTypeById(accidentTypeId));
                    return accident;
                });
    }

    public Collection<AccidentType> getAllAccidentType() {
        return jdbc.query("select * from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public Accident findById(int id) {
        return jdbc.queryForObject(
                "select * from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                },
                id
                );
    }

    public Collection<Rule> getAllRules() {
        return jdbc.query("select * from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    private AccidentType findAccidentTypeById(int id) {
        return jdbc.queryForObject(
                "select * from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                },
                id
        );
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject(
                "select * from rule where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id
        );
    }

    public List<Rule> findRulesByAccidentId(int id) {
        return jdbc.query("select * from accident_rule where accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }
}
