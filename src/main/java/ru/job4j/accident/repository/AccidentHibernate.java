package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate implements Store {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
         this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Accident> findAllAccidents() {
        return this.tx(
                session -> session
                        .createQuery(
                        "select distinct a from Accident a "
                            + "join fetch a.type as t "
                            + "join fetch a.rules "
                            + "order by a.id asc")
                        .list()
        );
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return this.tx(
                session -> session.createQuery(
                        "from AccidentType"
                ).list()
        );
    }

    @Override
    public List<Rule> findAllRules() {
        return this.tx(
                session -> session.createQuery(
                        "from Rule"
                ).list()
        );
    }


    public void save(Accident accident) {
         this.tx(
                session -> session.save(accident)
        );
    }

    @Override
    public void update(Accident accident) {
        this.tx(
                session -> {
                    session.update(accident);
                    return null;
                }
        );
    }

    @Override
    public Accident findById(int id) {
        return (Accident) this.tx(
                session -> session.createQuery(
                        "select distinct a from Accident a "
                        + "join fetch a.type as t "
                        + "join fetch a.rules "
                        + "where a.id = :id")
                .setParameter("id", id)
                .uniqueResult()
        );
    }

    public Rule findRuleById(int id) {
        return this.tx(
                session -> session.get(Rule.class, id)
        );
    }
}
