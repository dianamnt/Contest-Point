package com.contestpoint.repository;

import com.contestpoint.model.LocationContest;
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
public class LocationContestRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<LocationContest> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<LocationContest> cq = cb.createQuery(LocationContest.class);
        Root<LocationContest> root = cq.from(LocationContest.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        LocationContest LocationContest = session.byId(LocationContest.class).load(id);session.delete(LocationContest);
        session.flush();
        session.clear();
    }


    public void updateData(LocationContest LocationContest) {
        Session session = sessionFactory.getCurrentSession();
        session.update(LocationContest);
    }


    public void saveData(LocationContest LocationContest) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(LocationContest);
    }


    public LocationContest findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(LocationContest.class, id);

        }catch (Error e){
            return null;
        }
    }

}
