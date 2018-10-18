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

    public void storeJobApplication(JobApplication jobApplication) throws SQLException, NamingException {
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "insert into jobapplication(JobId,MemberId,ExpectedPay) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, jobApplication.getJobId());
        preparedStatement.setInt(2, jobApplication.getMemberId());
        preparedStatement.setDouble(3, jobApplication.getExpectedPay());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public List<JobApplicationForm> getJobApplicationList(int memberId) throws SQLException,NamingException{
        List<JobApplicationForm> jobApplicationFormList = new ArrayList<JobApplicationForm>();
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "select ja.Id , ja.JobId , ja.MemberId , j.Title , ja.ExpectedPay , j.PayPerHour , ja.Status " +
                "from jobapplication as ja , job as j , member as m " +
                "where ja.JobId = j.Id and ja.MemberId = m.Id and ja.MemberId=? and ja.Status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,memberId);
        preparedStatement.setString(2,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                JobApplicationForm jobApplicationForm = new JobApplicationForm();
                jobApplicationForm.setJobApplicationId(resultSet.getInt("Id"));
                jobApplicationForm.setJobId(resultSet.getInt("JobId"));
                jobApplicationForm.setMemberId(resultSet.getInt("MemberId"));
                jobApplicationForm.setJobTitle(resultSet.getString("Title"));
                jobApplicationForm.setExpectedPay(resultSet.getDouble("ExpectedPay"));
                jobApplicationForm.setPayPerHour(resultSet.getDouble("PayPerHour"));
                jobApplicationForm.setStatus(Status.valueOf(resultSet.getString("Status")));
                jobApplicationFormList.add(jobApplicationForm);
            }else{
                break;
            }
        }
        preparedStatement.close();
        connection.close();
        return jobApplicationFormList;
    }

    public void deleteJobApplication(int jobId, int memberId) throws SQLException,NamingException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql ="update jobapplication SET Status=? where JobId=? and MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.INACTIVE.name());
        preparedStatement.setInt(2,jobId);
        preparedStatement.setInt(3,memberId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

//    public List<JobApplicationForm> getJobApplicationsByJobId(int jobId) throws SQLException,NamingException{
//        List<JobApplicationForm> jobApplicationDTOList = new ArrayList<JobApplicationForm>();
//        Connection connection = JNDIHelper.getJNDIConnection();
//        String sql = "select ja.Id , ja.JobId , ja.MemberId ,j.Title,m.FirstName,m.LastName,ja.ExpectedPay,ja.Status " +
//                "from jobapplication as ja,member as m,job as j " +
//                "where ja.JobId = j.Id and ja.MemberId = m.Id and ja.JobId=? and ja.Status=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1,jobId);
//        preparedStatement.setString(2,Status.ACTIVE.name());
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while(true){
//            boolean contains = resultSet.next();
//            if(contains){
//                JobApplicationForm jobApplicationDTO = new JobApplicationForm();
//                jobApplicationDTO.setJobApplicationId(resultSet.getInt("Id"));
//                jobApplicationDTO.setJobId(resultSet.getInt("JobId"));
//                jobApplicationDTO.setMemberId(resultSet.getInt("MemberId"));
//                jobApplicationDTO.setJobTitle(resultSet.getString("Title"));
//                jobApplicationDTO.setExpectedPay(resultSet.getDouble("ExpectedPay"));
//                jobApplicationDTO.setSitterFirstName(resultSet.getString("FirstName"));
//                jobApplicationDTO.setSitterLastName(resultSet.getString("LastName"));
//                jobApplicationDTO.setStatus(Status.valueOf(resultSet.getString("Status")));
//                jobApplicationDTOList.add(jobApplicationDTO);
//            }else{
//                break;
//            }
//        }
//        preparedStatement.close();
//        connection.close();
//        return jobApplicationDTOList;
//    }

    public List<JobApplicationForm> getJobApplicationsByJobId(int jobId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "select ja.id , ja.jobId , ja.memberId ,j.jobTitle,m.firstName,m.lastName,ja.expectedPay,ja.status " +
                "from JobApplication as ja,Member as m,Job as j " +
                "where ja.jobId = j.id and ja.memberId = m.id and ja.jobId=? and ja.status=?";
        Query query = session.createQuery(hql);
        query.setInteger(0,jobId);
        query.setParameter(1,Status.ACTIVE);
        List<JobApplicationForm> jobApplicationFormList = query.list();
        transaction.commit();
        session.close();
        return jobApplicationFormList;
    }

    public JobApplicationForm getJobApplication(int jobId, int memberId) throws SQLException,NamingException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "select * from jobapplication where JobId=? and MemberId=? and status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,jobId);
        preparedStatement.setInt(2,memberId);
        preparedStatement.setString(3,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        JobApplicationForm jobApplicationForm = null;
        boolean contains = resultSet.next();
            if(contains) {
                jobApplicationForm = new JobApplicationForm();
                jobApplicationForm.setJobApplicationId(resultSet.getInt("Id"));
                jobApplicationForm.setJobId(resultSet.getInt("JobId"));
                jobApplicationForm.setMemberId(resultSet.getInt("MemberId"));
            }
        return jobApplicationForm;
    }

    public void closeJobApplicationByJobId(int jobId) throws SQLException,NamingException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql ="update jobapplication SET Status=? where JobId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.INACTIVE.name());
        preparedStatement.setInt(2,jobId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public void closeJobApplicationsByMemberId(int memberId) throws NamingException,SQLException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "update jobapplication SET Status=? where MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.INACTIVE.name());
        preparedStatement.setInt(2,memberId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public void deleteJobApplicationByJobId(int memberId) throws NamingException,SQLException{
        Connection connection = JNDIHelper.getJNDIConnection();
        String sql = "update jobapplication SET Status=? where jobapplication.JobId IN(select job.Id " +
                "from job where PostedBy=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.INACTIVE.name());
        preparedStatement.setInt(2,memberId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }
}
