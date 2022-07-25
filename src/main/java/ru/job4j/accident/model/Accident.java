package ru.job4j.accident.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Model of Accident
 */
@Entity
@Table(name = "accidents")
@NoArgsConstructor
@Getter
@Setter
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String text;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_types_id", nullable = false)
    private AccidentType type;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private Set<Rule> rules = new HashSet<>();

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
