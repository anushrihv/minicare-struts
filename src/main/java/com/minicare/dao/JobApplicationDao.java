package com.minicare.dao;

import com.minicare.dto.JobApplicationForm;
import com.minicare.model.JobApplication;
import com.minicare.model.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        session.close();
        return jobApplicationList;
    }

    public void deleteJobApplication(JobApplication jobApplication){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(jobApplication);
        transaction.commit();
        session.close();
    }

    public List<JobApplicationForm> getJobApplicationsByJobId(int jobId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from JobApplication where status=? and jobId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Status.ACTIVE);
        query.setInteger(1,jobId);
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
        session.close();
        return jobApplication;
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
