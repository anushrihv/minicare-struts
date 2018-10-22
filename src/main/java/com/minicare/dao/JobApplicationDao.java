package com.minicare.dao;

import com.minicare.dto.JobApplicationForm;
import com.minicare.model.JobApplication;
import com.minicare.model.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDao {
    static JobApplicationDao jobApplicationDao;

    static {
        jobApplicationDao = new JobApplicationDao();
    }

    private JobApplicationDao(){

    }

    public static JobApplicationDao getInstance(){
        return jobApplicationDao;
    }

    public void storeJobApplication(JobApplication jobApplication){
        jobApplication.setStatus(Status.ACTIVE);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(jobApplication);
        transaction.commit();
        session.close();
    }

    public List<JobApplication> getJobApplicationList(int memberId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from JobApplication where status=:status and memberId=:memberid";
        Query query = session.createQuery(hql);
        query.setParameter("status",Status.ACTIVE);
        query.setInteger("memberid",memberId);
        List<JobApplication> jobApplicationList = query.list();
        transaction.commit();
        session.close();
        return jobApplicationList;
    }

    public void deleteJobApplication(int jobId , int memberId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update JobApplication SET status=? where jobId=? and memberId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.INACTIVE);
        query.setInteger(1,jobId);
        query.setInteger(2,memberId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public List<JobApplicationForm> getJobApplicationsByJobId(int jobId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from JobApplication where status=? and jobId=?";
        Query query = session.createQuery(hql);
        query.setInteger(0,jobId);
        query.setParameter(1,Status.ACTIVE);
        List<JobApplicationForm> jobApplicationFormList = query.list();
        session.close();
        return jobApplicationFormList;
    }

    public JobApplication getJobApplication(int jobId , int memberId){
        JobApplication jobApplication = null ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from JobApplication where jobId=? and memberId=? and status=?";
        Query query = session.createQuery(hql);
        query.setInteger(0,jobId);
        query.setInteger(1,memberId);
        query.setParameter(2,Status.ACTIVE);
        List<JobApplication> jobApplicationList = query.list();
        if(jobApplicationList.size()>0){
            jobApplication = jobApplicationList.get(0);
        }
        transaction.commit();
        session.close();
        return jobApplication;
    }

    public void closeJobApplicationByJobId(int jobId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update JobApplication SET status=? where jobId = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.INACTIVE);
        query.setInteger(1,jobId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

//    public void closeJobApplicationsByMemberId(int memberId) throws NamingException,SQLException{
//        Connection connection = JNDIHelper.getJNDIConnection();
//        String sql = "update jobapplication SET Status=? where MemberId=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1,Status.INACTIVE.name());
//        preparedStatement.setInt(2,memberId);
//        preparedStatement.executeUpdate();
//
//        preparedStatement.close();
//        connection.close();
//    }

    public void closeJobApplicationsByMemberId(int memberId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update JobApplication SET status=? where memberId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.INACTIVE);
        query.setInteger(1,memberId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void deleteJobApplicationByJobId(int memberId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update JobApplication SET status=? where jobId IN(" +
                "select j.id from Job as j where postedBy=?)";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.INACTIVE);
        query.setInteger(1,memberId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
