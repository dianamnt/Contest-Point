package com.contestpoint.repository;

import com.contestpoint.model.Detail;
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
public class DetailRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Detail> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Detail> cq = cb.createQuery(Detail.class);
        Root<Detail> root = cq.from(Detail.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Detail Detail = session.byId(Detail.class).load(id);session.delete(Detail);
        session.flush();
        session.clear();
    }


    public void updateData(Detail Detail) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Detail);
    }


    public void saveData(Detail Detail) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(Detail);
    }


    public Detail findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(Detail.class, id);

        }catch (Error e){
            return null;
        }
    }

}
