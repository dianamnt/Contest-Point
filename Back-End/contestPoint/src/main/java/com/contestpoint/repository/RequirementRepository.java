package com.contestpoint.repository;

import com.contestpoint.model.Requirement;
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
public class RequirementRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Requirement> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Requirement> cq = cb.createQuery(Requirement.class);
        Root<Requirement> root = cq.from(Requirement.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Requirement Requirement = session.byId(Requirement.class).load(id);session.delete(Requirement);
        session.flush();
        session.clear();
    }


    public void updateData(Requirement Requirement) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Requirement);
    }


    public void saveData(Requirement Requirement) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(Requirement);
    }


    public Requirement findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(Requirement.class, id);

        }catch (Error e){
            return null;
        }
    }

}
