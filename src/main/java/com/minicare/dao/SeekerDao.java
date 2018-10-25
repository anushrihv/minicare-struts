package com.minicare.dao;

import com.minicare.model.Member;
import com.minicare.model.Seeker;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class SeekerDao {
    private static SeekerDao seekerDao;

    private SeekerDao(){

    }

    static {
        seekerDao = new SeekerDao();
    }

    public static SeekerDao getInstance(){
        return seekerDao;
    }


    public void insertSeeker(Seeker seeker){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(seeker);
        transaction.commit();
        session.close();
    }


    public Seeker getSeeker(int seekerId){
        Seeker seeker ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Seeker where memberId = ?";
        Query query = session.createQuery(hql);
        query.setInteger(0,seekerId);
        seeker =  (Seeker) query.uniqueResult();
        session.close();
        return seeker;
    }

    public void editSeeker(Seeker seeker){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(seeker);
        transaction.commit();
        session.close();
    }

    public void deleteSeeker(Seeker seeker){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(seeker);
        transaction.commit();
        session.close();
    }
}
