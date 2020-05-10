package com.contestpoint.repository;

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
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.byId(User.class).load(id);session.delete(user);
        session.flush();
        session.clear();
    }


    public void updateData(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }


    public void saveData(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(user);
    }


    public User findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(User.class, id);

        }catch (Error e){
            return null;
        }
    }


    public User findByEmailAndPassword(String email, String password){
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from User u where u.email = :email and u.password = :password")
                .setParameter("email", email)
                .setParameter("password", password);

        User foundUser = null;
        try {
            foundUser = (User) hql.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return foundUser;
    }

    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from User u where u.email = :email")
                .setParameter("email", email);
        User foundUser = null;
        try {
            foundUser = (User) hql.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return foundUser;
    }
}
