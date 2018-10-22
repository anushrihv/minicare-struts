package com.minicare.dao;

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
        Seeker seeker = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Seeker where memberId = ?";
        Query query = session.createQuery(hql);
        query.setInteger(0,seekerId);
        List<Seeker> seekerList = query.list();
        if(seekerList.size()>0)
            seeker = (Seeker) seekerList.get(0);
        transaction.commit();
        session.close();
        return seeker;
    }

    public void editSeeker(Seeker seeker){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Seeker SET numberOfChildren = ? , spouseName = ? where memberId = ?";
        Query query = session.createQuery(hql);
        query.setInteger(0,seeker.getNumberOfChildren());
        query.setString(1,seeker.getSpouseName());
        query.setInteger(2,seeker.getMemberId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
