package com.minicare.dao;

import com.minicare.dto.JobFormBean;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;
import com.minicare.model.Status;
import org.apache.commons.beanutils.converters.SqlDateConverter;

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

    public void storeJob(JobModel jobModel, MemberModel memberModel) throws ClassNotFoundException, SQLException {
        Connection connection = JDBCHelper.getConnection();

        int id = memberModel.getMemberId();

        String sql = "insert into job(Title,PostedBy,StartDateTime,EndDateTime,PayPerHour) values(?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,jobModel.getJobTitle());
        preparedStatement.setInt(2,id);
        preparedStatement.setTimestamp(3,jobModel.getStartDateTime());
        preparedStatement.setTimestamp(4,jobModel.getEndDateTime());
        preparedStatement.setDouble(5,jobModel.getPayPerHour());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public List<JobModel> getJobsById(MemberModel memberModel) throws ClassNotFoundException,SQLException{
        Connection connection = JDBCHelper.getConnection();
        List<JobModel> jobModelList = new ArrayList<JobModel>();

        int id = memberModel.getMemberId();

        String sql = "select * from job where Status=? and PostedBy=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Status.ACTIVE.name());
        preparedStatement.setInt(2,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (true){
           boolean contains = resultSet.next();
           if(contains){
               JobModel jobModel = new JobModel();
               jobModel.setId(resultSet.getInt("Id"));
               jobModel.setJobTitle(resultSet.getString("Title"));
               jobModel.setStartDateTime(resultSet.getTimestamp("StartDateTime"));
               jobModel.setEndDateTime(resultSet.getTimestamp("EndDateTime"));
               jobModel.setPayPerHour(resultSet.getInt("PayPerHour"));
               jobModel.setStatus(Status.valueOf(resultSet.getString("Status")));
               jobModelList.add(jobModel);
           }else{
               break;
           }
        }
        connection.close();
        return jobModelList;
    }

    public List<JobModel> getJobs() throws ClassNotFoundException,SQLException{
        Connection connection = JDBCHelper.getConnection();
        List<JobModel> jobModelList = new ArrayList<JobModel>();

        String sql = "select * from job where Status=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                JobModel jobModel = new JobModel();
                jobModel.setId(resultSet.getInt("Id"));
                jobModel.setJobTitle(resultSet.getString("Title"));
                jobModel.setStartDateTime(resultSet.getTimestamp("StartDateTime"));
                jobModel.setEndDateTime(resultSet.getTimestamp("EndDateTime"));
                jobModel.setPayPerHour(resultSet.getInt("PayPerHour"));
                jobModel.setStatus(Status.valueOf(resultSet.getString("Status")));
                jobModelList.add(jobModel);
            }else{
                break;
            }
        }
        connection.close();
        return jobModelList;
    }

    public JobModel getJobByJobId(int jobId) throws ClassNotFoundException,SQLException{
        Connection connection = JDBCHelper.getConnection();
        String sql ="select * from job where Id=? and status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,jobId);
        preparedStatement.setString(2,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            JobModel jobModel = new JobModel();
            jobModel.setId(resultSet.getInt("Id"));
            jobModel.setJobTitle(resultSet.getString("Title"));
            jobModel.setPostedBy(resultSet.getInt("PostedBy"));
            jobModel.setStartDateTime(resultSet.getTimestamp("StartDateTime"));
            jobModel.setEndDateTime(resultSet.getTimestamp("EndDateTime"));
            jobModel.setPayPerHour(resultSet.getDouble("PayPerHour"));
            jobModel.setStatus(Status.valueOf(resultSet.getString("Status")));
            return jobModel;
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

    public void updateJob(JobModel jobModel) throws ClassNotFoundException, SQLException {
        Connection connection = JDBCHelper.getConnection();
        String sql ="update job SET Title=? , StartDateTime=? , EndDateTime=? , PayPerHour=? where Id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,jobModel.getJobTitle());
        preparedStatement.setTimestamp(2,jobModel.getStartDateTime());
        preparedStatement.setTimestamp(3,jobModel.getEndDateTime());
        preparedStatement.setDouble(4,Double.valueOf(jobModel.getPayPerHour()));
        preparedStatement.setInt(5,jobModel.getId());
        preparedStatement.executeUpdate();
        connection.close();
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
