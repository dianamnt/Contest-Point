package com.contestpoint.repository;

import com.contestpoint.model.Quiz;
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
public class QuizRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Quiz> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Quiz> cq = cb.createQuery(Quiz.class);
        Root<Quiz> root = cq.from(Quiz.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    public void removeData(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Quiz Quiz = session.byId(Quiz.class).load(id);session.delete(Quiz);
        session.flush();
        session.clear();
    }


    public void updateData(Quiz Quiz) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Quiz);
    }


    public void saveData(Quiz Quiz) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(Quiz);
    }


    public Quiz findById(Long id) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(Quiz.class, id);

        }catch (Error e){
            return null;
        }
    }

}
