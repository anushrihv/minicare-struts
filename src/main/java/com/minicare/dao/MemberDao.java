package com.minicare.dao;

import com.minicare.model.Member;
import com.minicare.model.Status;
import com.minicare.model.Type;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberDao {
    private static MemberDao memberDao;

    private MemberDao() {

    }

    static {
        memberDao = new MemberDao();
    }

    public static MemberDao getInstance() {
        return memberDao;
    }


    public List<Member> getMember(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql ="FROM com.minicare.model.Member where email = :email_id and status= :active_status" ;
        Query query = session.createQuery(hql) ;
        query.setParameter("email_id",email);
        query.setParameter("active_status",Status.ACTIVE);
        List result = query.list();
        transaction.commit();
        session.close();
        return result;
    }


    public void deleteMember(int memberId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Member SET status=? where id=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.INACTIVE);
        query.setInteger(1,memberId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }


    public void editMember(Member member){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Member SET firstName = ? , lastName = ? ,phoneNumber = ?," +
                "address= ? where memberId = ?";
        Query query = session.createQuery(hql);
        query.setString(0,member.getFirstName());
        query.setString(1,member.getLastName());
        query.setLong(2,member.getPhoneNumber());
        query.setString(3,member.getAddress());
        query.setInteger(4,member.getMemberId());
        query.executeUpdate();
        transaction.commit();
        session.close();

    }


    public void updatePassword(int memberId , String newPassword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Member SET password = ? where memberId = ?";
        Query query = session.createQuery(hql);
        query.setString(0,newPassword);
        query.setInteger(1,memberId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public List<Member> searchMember(String email , Type type){
        List<Member> memberList ;
        Type searchType ;
        if(type.name().equals("SITTER")){
            searchType = Type.SEEKER ;
        }
        else{
            searchType = Type.SITTER ;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Member where emailAddress like ? and type = ? and status = ?";
        Query query = session.createQuery(hql);
        query.setString(0,"%"+email+"%");
        query.setParameter(1,searchType);
        query.setParameter(2,Status.ACTIVE);
        memberList = query.list();
        transaction.commit();
        session.close();
        return memberList;
    }
}
