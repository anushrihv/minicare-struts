package com.minicare.dao;

import com.minicare.model.Job;
import com.minicare.model.Member;
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

//    public void storeJob(Job job, Member member) throws ClassNotFoundException, SQLException {
//        Connection connection = JDBCHelper.getConnection();
//
//        int id = member.getMemberId();
//
//        String sql = "insert into job(Title,PostedBy,StartDateTime,EndDateTime,PayPerHour) values(?,?,?,?,?)";
//        preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, job.getJobTitle());
//        preparedStatement.setInt(2,id);
//        preparedStatement.setTimestamp(3, job.getStartDateTime());
//        preparedStatement.setTimestamp(4, job.getEndDateTime());
//        preparedStatement.setDouble(5, job.getPayPerHour());
//        preparedStatement.executeUpdate();
//        connection.close();
//    }

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

    public List<Job> getJobs() throws ClassNotFoundException,SQLException{
        Connection connection = JDBCHelper.getConnection();
        List<Job> jobList = new ArrayList<Job>();

        String sql = "select * from job where Status=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                Job job = new Job();
                job.setId(resultSet.getInt("Id"));
                job.setJobTitle(resultSet.getString("Title"));
                job.setStartDateTime(resultSet.getTimestamp("StartDateTime"));
                job.setEndDateTime(resultSet.getTimestamp("EndDateTime"));
                job.setPayPerHour(resultSet.getInt("PayPerHour"));
                job.setStatus(Status.valueOf(resultSet.getString("Status")));
                jobList.add(job);
            }else{
                break;
            }
        }
        connection.close();
        return jobList;
    }

    public Job getJobByJobId(int jobId) throws ClassNotFoundException,SQLException{
        Connection connection = JDBCHelper.getConnection();
        String sql ="select * from job where Id=? and status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,jobId);
        preparedStatement.setString(2,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Job job = new Job();
            job.setId(resultSet.getInt("Id"));
            job.setJobTitle(resultSet.getString("Title"));
            job.setPostedBy(resultSet.getInt("PostedBy"));
            job.setStartDateTime(resultSet.getTimestamp("StartDateTime"));
            job.setEndDateTime(resultSet.getTimestamp("EndDateTime"));
            job.setPayPerHour(resultSet.getDouble("PayPerHour"));
            job.setStatus(Status.valueOf(resultSet.getString("Status")));
            return job;
        }
        return null;
    }

    public void closeJob(int jobId) throws ClassNotFoundException,SQLException{
        Connection connection = JDBCHelper.getConnection();
        String sql ="update job SET Status=? where Id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.INACTIVE.name());
        preparedStatement.setInt(2,jobId);
        preparedStatement.executeUpdate();
        connection.close();
    }

//    public void updateJob(Job job) throws ClassNotFoundException, SQLException {
//        Connection connection = JDBCHelper.getConnection();
//        String sql ="update job SET Title=? , StartDateTime=? , EndDateTime=? , PayPerHour=? where Id=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, job.getJobTitle());
//        preparedStatement.setTimestamp(2, job.getStartDateTime());
//        preparedStatement.setTimestamp(3, job.getEndDateTime());
//        preparedStatement.setDouble(4,Double.valueOf(job.getPayPerHour()));
//        preparedStatement.setInt(5, job.getId());
//        preparedStatement.executeUpdate();
//        connection.close();
//    }

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

    public void closeJobByMemberId(int memberId) throws ClassNotFoundException,SQLException{
        Connection connection = JDBCHelper.getConnection();
        String sql = "update job SET Status=? where PostedBy=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.INACTIVE.name());
        preparedStatement.setInt(2,memberId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
}
