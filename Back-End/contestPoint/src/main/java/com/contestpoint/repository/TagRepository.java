package com.contestpoint.repository;

import com.contestpoint.model.Tag;
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
import java.util.List;

@Repository
public class TagRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Tag> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
        Root<Tag> root = cq.from(Tag.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Tag tag = session.byId(Tag.class).load(id);session.delete(tag);
        session.flush();
        session.clear();
    }


    public void updateData(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        session.update(tag);
    }


    public Long saveData(Tag tag) {
        Session currentSession = sessionFactory.getCurrentSession();
        Long id = (Long) currentSession.save(tag);
        return id;
    }


    public Tag findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(Tag.class, id);

        }catch (Error e){
            return null;
        }
    }

    public Tag findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Tag t where t.tagName = :name")
                .setParameter("name", name);
        Tag foundTag = null;
        try {
            foundTag = (Tag) hql.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return foundTag;
    }

}
