package com.contestpoint.repository;

import com.contestpoint.model.ParticipationContract;
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
public class ParticipationContractRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<ParticipationContract> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ParticipationContract> cq = cb.createQuery(ParticipationContract.class);
        Root<ParticipationContract> root = cq.from(ParticipationContract.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        ParticipationContract ParticipationContract = session.byId(ParticipationContract.class).load(id);session.delete(ParticipationContract);
        session.flush();
        session.clear();
    }


    public void updateData(ParticipationContract ParticipationContract) {
        Session session = sessionFactory.getCurrentSession();
        session.update(ParticipationContract);
    }


    public Long saveData(ParticipationContract participationContract) {
        Session currentSession = sessionFactory.getCurrentSession();
        Long id = (Long) currentSession.save(participationContract);
        return id;
    }


    public ParticipationContract findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(ParticipationContract.class, id);

        }catch (Error e){
            return null;
        }
    }

    public ParticipationContract isEnrolled(Long userId, Long contestId){
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from ParticipationContract u where u.user.id = :userId and u.contest.id = :contestId")
                .setParameter("userId", userId)
                .setParameter("contestId", contestId);

        ParticipationContract foundLike = null;
        try {
            foundLike = (ParticipationContract) hql.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return foundLike;
    }

}
