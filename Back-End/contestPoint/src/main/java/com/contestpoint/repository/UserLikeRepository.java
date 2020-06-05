package com.contestpoint.repository;

import com.contestpoint.model.User;
import com.contestpoint.model.UserLike;
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
public class UserLikeRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<UserLike> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserLike> cq = cb.createQuery(UserLike.class);
        Root<UserLike> root = cq.from(UserLike.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserLike like = session.byId(UserLike.class).load(id);session.delete(like);
        session.flush();
        session.clear();
    }


    public void updateData(UserLike like) {
        Session session = sessionFactory.getCurrentSession();
        session.update(like);
    }


    public Long saveData(UserLike like) {
        Session currentSession = sessionFactory.getCurrentSession();
        Long id = (Long) currentSession.save(like);
        return id;
    }


    public UserLike findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(UserLike.class, id);

        }catch (Error e){
            return null;
        }
    }

    public UserLike isLiked(Long userId, Long contestId){
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from UserLike u where u.user.id = :userId and u.contest.id = :contestId")
                .setParameter("userId", userId)
                .setParameter("contestId", contestId);

        UserLike foundLike = null;
        try {
            foundLike = (UserLike) hql.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return foundLike;
    }


}
