package com.contestpoint.repository;

import com.contestpoint.model.Answer;
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
public class AnswerRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Answer> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Answer> cq = cb.createQuery(Answer.class);
        Root<Answer> root = cq.from(Answer.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Answer Answer = session.byId(Answer.class).load(id);session.delete(Answer);
        session.flush();
        session.clear();
    }


    public void updateData(Answer Answer) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Answer);
    }


    public void saveData(Answer Answer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(Answer);
    }


    public Answer findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(Answer.class, id);

        }catch (Error e){
            return null;
        }
    }

}
