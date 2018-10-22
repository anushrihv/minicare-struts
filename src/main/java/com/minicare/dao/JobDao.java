package com.minicare.dao;

import com.minicare.model.Job;
import com.minicare.model.Member;
import com.minicare.model.Seeker;
import com.minicare.model.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDao {
    static JobDao jobDao;
    PreparedStatement preparedStatement;

    static{
        jobDao = new JobDao();
    }

    private JobDao(){

    }

    public static JobDao getInstance(){
        return jobDao;
    }

    public void storeJob(Job job , Member member){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        job.setPostedBy(member.getMemberId());
        job.setStatus(Status.ACTIVE);
        session.save(job);
        transaction.commit();
        session.close();
    }

    public List<Job> getJobsById(Member member){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Job where status=? and postedBy = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.ACTIVE);
        query.setInteger(1,member.getMemberId());
        List<Job> jobList = query.list();
        transaction.commit();
        session.close();
        return jobList;
    }

    public List<Job> getJobs(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Job where status=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.ACTIVE);
        List<Job> jobList = query.list();
        transaction.commit();
        session.close();
        return jobList;
    }

    public Job getJobByJobId(int jobId){
        Job job = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Job where id=? and status=?";
        Query query = session.createQuery(hql);
        query.setInteger(0,jobId);
        query.setParameter(1,Status.ACTIVE);
        List<Job> jobList = query.list();
        if(jobList.size()>0){
            job = jobList.get(0);
        }
        session.close();
        return job;
    }

//    public void closeJob(int jobId) throws ClassNotFoundException,SQLException{
//        Connection connection = JDBCHelper.getConnection();
//        String sql ="update job SET Status=? where Id=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1,Status.INACTIVE.name());
//        preparedStatement.setInt(2,jobId);
//        preparedStatement.executeUpdate();
//        connection.close();
//    }

    public void closeJob(int jobId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Job SET status=? where id=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.INACTIVE);
        query.setInteger(1,jobId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void updateJob(Job job){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Job SET jobTitle=? , startDateTime=? , endDateTime=? , payPerHour=? where id=?";
        Query query = session.createQuery(hql);
        query.setString(0,job.getJobTitle());
        query.setParameter(1,job.getStartDateTime());
        query.setParameter(2,job.getEndDateTime());
        query.setDouble(3,job.getPayPerHour());
        query.setInteger(4,job.getId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void closeJobByMemberId(int memberId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Job SET status=? where postedBy=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.INACTIVE);
        query.setInteger(1,memberId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
