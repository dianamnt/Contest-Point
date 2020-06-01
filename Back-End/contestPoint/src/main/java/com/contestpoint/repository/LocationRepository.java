package com.contestpoint.repository;

import com.contestpoint.model.Location;
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
public class LocationRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Location> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Location> cq = cb.createQuery(Location.class);
        Root<Location> root = cq.from(Location.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Location Location = session.byId(Location.class).load(id);session.delete(Location);
        session.flush();
        session.clear();
    }


    public void updateData(Location Location) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Location);
    }


    public Long saveData(Location Location) {
        Session currentSession = sessionFactory.getCurrentSession();
        Long id = (Long) currentSession.save(Location);
        return id;
    }


    public Location findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(Location.class, id);

        }catch (Error e){
            return null;
        }
    }

}
