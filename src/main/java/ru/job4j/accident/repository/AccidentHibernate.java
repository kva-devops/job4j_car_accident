package ru.job4j.accident.repository;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class AccidentHibernate {

    private static final Logger LOG = LogManager.getLogger(AccidentHibernate.class.getName());

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            LOG.info(accident.getRules().size());
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select a from Accident a join fetch a.type order by a.id asc ")
                    .list();
        }
    }

    public List getAllAccidentType() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType")
                    .list();
        }
    }

    public List<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule")
                    .list();
        }
    }

    public Rule findRuleById(int id) {
        try (Session session = sf.openSession()) {
            return (Rule) session
                    .createQuery("from Rule where id=:id")
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }
}
