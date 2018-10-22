package com.minicare.dao;

import com.minicare.model.*;
import com.minicare.model.Member;
import com.minicare.model.Sitter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SitterDao{
    private static SitterDao sitterDao;

    private SitterDao(){

    }

    static{
        sitterDao = new SitterDao();
    }

    public static SitterDao getInstance(){
        return sitterDao;
    }


    public void insertSitter(Sitter sitter){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(sitter);
        transaction.commit();
        session.close();
    }


    public void deleteSitter(int memberId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Sitter SET status=? where memberId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.INACTIVE);
        query.setInteger(1,memberId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public Sitter getSitter(int sitterId){
        Sitter sitter = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Sitter where memberId = ?";
        Query query = session.createQuery(hql);
        query.setInteger(0,sitterId);
        List<Sitter> sitterList = query.list();
        if(sitterList.size()>0)
            sitter = (Sitter) sitterList.get(0);
        transaction.commit();
        session.close();
        return sitter;
    }

    public void editSitter(Sitter sitter){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Sitter SET yearsOfExperience=? , expectedPay=? where memberId=?";
        Query query = session.createQuery(hql);
        query.setInteger(0,sitter.getYearsOfExperience());
        query.setDouble(1,sitter.getExpectedPay());
        query.setInteger(2,sitter.getMemberId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
