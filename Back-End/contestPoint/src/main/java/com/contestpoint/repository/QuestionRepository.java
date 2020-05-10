package com.contestpoint.repository;

import com.contestpoint.model.Question;
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
public class QuestionRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Question> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Question> cq = cb.createQuery(Question.class);
        Root<Question> root = cq.from(Question.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Question Question = session.byId(Question.class).load(id);session.delete(Question);
        session.flush();
        session.clear();
    }


    public void updateData(Question Question) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Question);
    }


    public void saveData(Question Question) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(Question);
    }


    public Question findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(Question.class, id);

        }catch (Error e){
            return null;
        }
    }

}
