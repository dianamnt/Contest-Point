package com.contestpoint.repository;

import com.contestpoint.model.TagContest;
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
public class TagContestRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<TagContest> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TagContest> cq = cb.createQuery(TagContest.class);
        Root<TagContest> root = cq.from(TagContest.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        TagContest TagContest = session.byId(TagContest.class).load(id);session.delete(TagContest);
        session.flush();
        session.clear();
    }


    public void updateData(TagContest TagContest) {
        Session session = sessionFactory.getCurrentSession();
        session.update(TagContest);
    }


    public void saveData(TagContest TagContest) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(TagContest);
    }


    public TagContest findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(TagContest.class, id);

        }catch (Error e){
            return null;
        }
    }

}
