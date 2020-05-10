package com.contestpoint.repository;

import com.contestpoint.model.Contest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ContestRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Contest> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Contest> cq = cb.createQuery(Contest.class);
        Root<Contest> root = cq.from(Contest.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Contest Contest = session.byId(Contest.class).load(id);session.delete(Contest);
        session.flush();
        session.clear();
    }


    public void updateData(Contest Contest) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Contest);
    }


    public void saveData(Contest Contest) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(Contest);
    }


    public Contest findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(Contest.class, id);

        }catch (Error e){
            return null;
        }
    }

}
