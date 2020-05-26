package com.contestpoint.repository;

import com.contestpoint.model.Contest;
import com.contestpoint.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public Contest findByEverything(String name, Long userId){
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Contest c where c.contestName = :name and c.user.id = :userId")
                .setParameter("name", name)
                .setParameter("userId", userId);

        Contest foundContest = null;
        try {
            foundContest = (Contest) hql.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return foundContest;
    }

}
