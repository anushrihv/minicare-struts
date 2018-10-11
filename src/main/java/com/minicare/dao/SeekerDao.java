package com.minicare.dao;

import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class SeekerDao {
    private static SeekerDao seekerDao;
    private PreparedStatement preparedStatement;

    private SeekerDao(){

    }

    static {
        seekerDao = new SeekerDao();
    }

    public static SeekerDao getInstance(){
        return seekerDao;
    }

    public void insertSeeker(SeekerModel seekerModel) throws ClassNotFoundException, SQLException {
            Connection connection = JDBCHelper.getConnection();
            MemberDao memberDao = MemberDao.getInstance();
            memberDao.insertMember(connection, seekerModel);

            Set<MemberModel> memberModelSet = memberDao.getMember(seekerModel.getEmail());
            Iterator<MemberModel> iterator = memberModelSet.iterator();
            MemberModel memberModel = iterator.next();
            int id = memberModel.getMemberId();

            String sql = "insert into seeker(MemberId,NumberOfChildren,SpouseName) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, seekerModel.getNumberOfChildren());
            preparedStatement.setString(3, seekerModel.getSpouseName());
            preparedStatement.executeUpdate();

            try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
            try { JDBCHelper.closeConnection(); } catch (Exception e) { /* ignored */ }
    }

    public SeekerModel getSeeker(int seekerId) throws ClassNotFoundException, SQLException{
        SeekerModel seekerModel = new SeekerModel();
        Connection connection = JDBCHelper.getConnection();
        String sql = "select MemberId,NumberOfChildren,SpouseName from seeker where MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,seekerId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            seekerModel.setMemberId(resultSet.getInt("MemberId"));
            seekerModel.setNumberOfChildren(resultSet.getInt("NumberOfChildren"));
            seekerModel.setSpouseName(resultSet.getString("SpouseName"));
        }

        preparedStatement.close();
        connection.close();
        return seekerModel;
    }

    public void editSeeker(SeekerModel seekerModel) throws ClassNotFoundException, SQLException{
        Connection connection = JDBCHelper.getConnection();
        String sql = "update seeker SET NumberOfChildren=? , SpouseName=? where MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,seekerModel.getNumberOfChildren());
        preparedStatement.setString(2,seekerModel.getSpouseName());
        preparedStatement.setInt(3,seekerModel.getMemberId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
}
